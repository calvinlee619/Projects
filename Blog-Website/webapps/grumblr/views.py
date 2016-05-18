from mimetypes import guess_type
from django.contrib.auth.tokens import default_token_generator
from django.http import Http404, HttpResponse
from django.shortcuts import render, redirect,get_object_or_404
from django.core.exceptions import ObjectDoesNotExist
from django.core.urlresolvers import reverse

# Decrator to use built-in authentication system
from django.contrib.auth.decorators import login_required
from django.contrib.auth.models import User
from django.contrib.auth import login, authenticate
from smtplib import SMTP
from django.core.mail import send_mail
from grumblr.forms import *

# Create your views here.

@login_required
def home(request):
	# Sets up list of just the Login-user's blog
	global_stream_flag = True
	return render(request,'GlobalStream.html',{'global_stream_flag':global_stream_flag})

@login_required()
def get_blogs(request):
	max_entry = LogEntry.get_max_id()
	blogs = Blog.objects.all().order_by('time').reverse()
	context = {"max_entry":max_entry,"blogs":blogs}
	return render(request,"blogs.json",context,content_type='application/json')


@login_required()
def get_changes(request,max_id=-1):
	max_entry = LogEntry.get_max_id()
	blogs = Blog.get_changes(max_id).order_by('time').reverse()
	context = {"max_entry":max_entry,"blogs":blogs}
	return render(request,"blogs.json",context,content_type='application/json')

@login_required()
def follower_stream(request):
	return render(request,'GlobalStream.html')

@login_required()
def get_follower_blogs(request):
	max_entry=LogEntry.get_max_id()
	followers = UserInfo.get_Followers(user=request.user).all()
	blogs = Blog.objects.filter(user__in=followers).order_by('time').reverse()
	context = {"max_entry":max_entry,"blogs":blogs}
	return render(request,"blogs.json",context,content_type="application/json")

@login_required()
def get_follower_changes(request,max_id=-1):
	max_entry = LogEntry.get_max_id()
	followers = UserInfo.get_Followers(user=request.user).all()
	blogs = Blog.objects.filter(logentry__gt=max_id,user__in=followers).order_by('time').reverse()
	context = {"max_entry":max_entry,"blogs":blogs}
	return render(request,"blogs.json",context,content_type="application/json")

# Allow login-user to follow other users
@login_required()
def follow(request,id):
	user = User.objects.get(id=id)
	user_info = UserInfo.objects.get(user=request.user)
	user_info.followers.add(user)
	user_info.save()

	return redirect(reverse('other_profile',kwargs={'id':user.id}))

# allow user to unfollow other user
@login_required()
def unfollow(request,id):
	follower = User.objects.get(id = id)
	user_info = UserInfo.objects.get(user=request.user)
	user_info.followers.remove(follower)

	return redirect(reverse("other_profile",kwargs={'id':follower.id}))

# user's profile page
@login_required
def profile(request):
	user_info = UserInfo.objects.get(user = request.user)
	flag= True
	return render(request,'Profile.html',{'user_info':user_info,'flag':flag})

# allow login user to browse other user's profile
@login_required
def other_profile(request, id):
	errors = []
	try:
		user = User.objects.get(id=id)
		user_info = UserInfo.objects.get(user=user)
		owner_info = UserInfo.objects.get(user=request.user)
		if len(owner_info.followers.filter(id=id))>0:
			flag_follow = True
		else:
			flag_follow = False

	except ObjectDoesNotExist:
		errors.append('The user does not exist')

	if errors:
		return render(request,'GlobalStream.html',{'errors':errors})
	else:
		if user==request.user:
			return redirect(reverse('profile'))
		else:
			context = {'user_info':user_info,'flag_follow':flag_follow}
			return render(request,'Profile.html',context)


@login_required()
def get_profile_blogs(request,id):
	user = get_object_or_404(User,id=id)
	blogs = Blog.objects.filter(user=user).order_by('time').reverse()
	context = {"max_entry":-1,"blogs":blogs}
	return render(request,"blogs.json",context,content_type="application/json")


@login_required
def post_blog(request):
	form = BlogForm(request.POST)
	if not form.is_valid():
		return HttpResponse("Form is not valid")

	try:
		user_info = UserInfo.objects.get(user = request.user)
		text = form.cleaned_data["message"]
		new_message = Blog(text=text,user=request.user,\
									time = datetime.now(), user_info=user_info)
		new_message.save()
		log_entry = LogEntry(blog = new_message,op='add')
		log_entry.save()
	except ObjectDoesNotExist:
		raise Http404

	return HttpResponse("")


@login_required()
def add_comment(request,id):
	form = BlogForm(request.POST)
	if not form.is_valid():
		return HttpResponse("Message is not valid")

	try:
		blog = Blog.objects.get(id=id)
		user = request.user
		user_info = UserInfo.objects.get(user=user)
		text = form.cleaned_data['message']
		new_comment = Comment(blog=blog,user_info=user_info,\
							  time=datetime.now(),text=text)
		new_comment.save()
		log_entry = LogEntry(blog=blog,op='edit')
		log_entry.save()
	except ObjectDoesNotExist:
		return HttpResponse("The blog or user doesnot exist")

	context = {"comment":new_comment}
	return render(request,"comment.json",context,content_type="application/json")

# Allow user to edit his or her profile
@login_required()
def edit_profile(request):
	context= {}

	userinfo_to_edit = get_object_or_404(UserInfo,user=request.user)
	email = userinfo_to_edit.user.email

	if request.method == 'GET':
		form = UserForm(instance=userinfo_to_edit)
		return render(request,'edit_profile.html',{'form':form,'userinfo_to_edit':userinfo_to_edit})

	form = UserForm(request.POST,request._files,instance=userinfo_to_edit)
	context['form'] = form

	if not form.is_valid():
		return render(request,'edit_profile.html',context)

	user = userinfo_to_edit.user
	user.email = request.POST['email']
	user.save()
	form.save()
	return redirect(reverse('profile'))

@login_required()
def edit_password(request):

	user = request.user

	if request.method == "GET":
		form = ResetPasswordForm()
		return render(request,"ResetPassword.html",{'form':form})

	form = ResetPasswordForm(request.POST)

	if not form.is_valid():
		return render(request,"ResetPassword.html",{'form':form})

	password = form.cleaned_data["password1"]
	user.set_password(password)
	user.save()

	user= authenticate(username = user.username,
						   password = form.cleaned_data["password1"])

	login(request,user)
	return redirect(reverse("profile"))


@login_required()
def get_photo(request,id):
	user = User.objects.get(id=id)
	user_info = get_object_or_404(UserInfo,user = user)

	content_type = guess_type(user_info.pitcture.name)
	return HttpResponse(user_info.pitcture,content_type = content_type)



def send_password_confirm(request):
	context = {}

	if request.method == "GET":
		form = PasswordChangeForm()
		return render(request,"PasswordChange.html",{'form':form})

	form = PasswordChangeForm(request.POST)

	if not form.is_valid():
		return render(request,"PasswordChange.html",{'form':form})

	username = form.cleaned_data['username']
	user = User.objects.get(username = username)
	token = default_token_generator.make_token(user)

	email_body = """ This email is comfirmation for your password change.Please click the link below to
	change your password.

	http://%s%s
	""" % (request.get_host(),reverse('confirm',args={token,username}))

	send_mail(subject = "Password Change",
			   message = email_body,
			   from_email = "kangw@andrew.cmu.edu",
			   recipient_list = [user.email])

	return render(request,"PasswordChangeEmailConfirm.html",context)


def comfirm(request,username,token):
	user = get_object_or_404(User,username=username)

	if not default_token_generator.check_token(user,token):
		raise Http404

	if request.method == "GET":
		form = ResetPasswordForm()
		return render(request,"ResetPassword.html",{'form':form})

	form = ResetPasswordForm(request.POST)

	if not form.is_valid():
		return render(request,"ResetPassword.html",{'form':form})

	password = form.cleaned_data["password1"]
	user.set_password(password)
	user.save()

	return render(request,"ResetPasswordSuccess.html",{'form':form})


def register(request):
	context = {}

	# display the registration form if this is a GET request
	if request.method =="GET":
		context['form'] = RegisterForm()
		return render(request,'Register.html',context)

	form = RegisterForm(request.POST)
	context['form'] = form

	# Check the data
	if not form.is_valid():
		return render(request,"Register.html",context)

	# Create a new user from valid data
	new_user = User.objects.create_user(username = form.cleaned_data['username'],\
										password = form.cleaned_data['password1'],\
										email=form.cleaned_data['email'])
	new_user.save()

	user_info = UserInfo(first_name = form.cleaned_data['first_name'],\
						last_name = form.cleaned_data['last_name'],\
						user = new_user)
	user_info.save()

	# Logs in the new user and redirect to home page

	new_user = authenticate(username = form.cleaned_data['username'],\
							password = form.cleaned_data['password1'])
	login(request,new_user)
	return redirect(reverse("profile"))









