from django.db import models
from django.contrib.auth.models import User
from datetime import datetime
# Create your models here.
from django.db.models import Max


class UserInfo(models.Model):
	first_name = models.CharField(max_length = 40)
	last_name = models.CharField(max_length = 40)
	age = models.IntegerField(default=0,blank=True)
	short_blog = models.CharField(max_length=420,default="",blank=True)
	user = models.OneToOneField(User,related_name="UserInfo_user")
	pitcture = models.ImageField(upload_to="photos",blank= True,default="photos/1.jpg")
	followers = models.ManyToManyField(User,blank=True,related_name="UserInfo_followers")

	def __unicode__(self):
		return self.first_name + " " + self.last_name

	def get_firstName(self):
		return self.first_name

	def get_lastName(self): 
		return self.last_name

	def get_fullName(self):
		return self.first_name + " " + self.last_name

	@staticmethod
	def get_UserInfo(user):
		return UserInfo.objects.get(user = user)

	@staticmethod
	def get_Followers(user):
		user_info =UserInfo.objects.get(user=user)
		followers = user_info.followers
		return followers


class Blog(models.Model):
	text = models.CharField(max_length = 42)
	time = models.DateTimeField("%Y-%m-%d %H:%M:%S")
	user = models.ForeignKey(User)
	user_info = models.ForeignKey(UserInfo)

	def __unicode__(self):
		return self.text

	def get_text(self):
		return self.text

	def get_time(self):
		return self.time

	def get_name(self):
		return self.user_info.get_fullName()

	@staticmethod
	def get_changes(logentry_id=-1):
		return Blog.objects.filter(logentry__gt=logentry_id).order_by('time').distinct()

	@property
	def html(self):
		user_id = self.user.id
		blog_id = self.id
		text = "<div class='blog'><h3 class='blog-post-title'>\
			   		<img src='/get_photo/%d' class='userphoto'><a href='other_profile/%d'>%s</a></h3> \
			   		<p class='blog-post-meta'>%s</p>\
				    	<blockquote>\
				    		<p>%s</p>\
				    		<button class ='btn btn-sm btn-info commentbtn togglecomment-btn' toggle-id=%s> \
			   					<span class='glyphicon glyphicon-edit'></span> \
			   				</button>\
							<hr>\
				    	</blockquote>\
			   <div class='post-footer'>\
                	<div class='input-group comment-area' style='display:none' commentarea-id=%s>\
                    	<input class='form-control commentInput' placeholder='Add a comment' type='text' input-id=%s>\
		                	<span class='input-group-addon'>\
                        		<a class='addcomment-btn' btn-id=%s><button class='glyphicon glyphicon-plus'></button></a>\
                        	</span>\
                	</div>" % (user_id,user_id,self.get_name(),self.time.strftime("%Y-%m-%d %H:%M:%S"),\
							   self.get_text(),blog_id,blog_id,blog_id,blog_id)

		comments = Comment.objects.filter(blog = self).order_by('time').reverse()
		for comment in comments:
			photo_url = '/get_photo/'+str(comment.user_info.user.id)
			comment_text = "<ul class='comments-list'>\
								<li class='comment'>\
									<a class='pull-left'>\
										<img class='avatar' src=%s alt='avatar'>\
									</a>\
									<div class='comment-body'>\
										<div class='comment-heading'>\
											<h4 class='user'>%s</h4>\
											<h5 class='time'>%s</h5>\
											</div>\
										<p>%s</p>\
									</div>\
								</li>\
							</ul>" % (photo_url,\
									  comment.user_info.get_fullName(),\
									  comment.time.strftime("%Y-%m-%d %H:%M:%S"),\
									  comment.text)
			text += comment_text
		text += "</div>"
		return text


class Comment(models.Model):
	blog = models.ForeignKey(Blog)
	text = models.CharField(max_length=42)
	time = models.DateTimeField("%Y-%m-%d %H:%M:%S")
	user_info = models.ForeignKey(UserInfo)

	def __unicode__(self):
		return "Comment(%d,%s,%s)" % (self.id,self.blog,self.text)

	def html(self):
		name = self.user_info.get_fullName()
		time = self.time.strftime("%Y-%m-%d %H:%M:%S")
		text = self.text
		photo_url = "'/get_photo/"+str(self.user_info.user.id)+"'"
		comment_text = '''<ul class='comments-list'><li class='comment'><a class='pull-left'>
		<img class='avatar' src={} alt='avatar'></a><div class='comment-body'><div class='comment-heading'>
		<h4 class='user'>{}</h4><h5 class='time'>{}</h5>
		</div><p>{}</p></div></li></ul>'''.format(photo_url,name,time,text).replace('\n','').replace('\t','')
		print(comment_text)
		return comment_text

class LogEntry(models.Model):
	blog = models.ForeignKey(Blog)
	op = models.CharField(max_length=4,choices=[('add','add'),
												('edit','edit')])

	def __unicode__(self):
		return "LogEntry(%d,%s)" % (self.id,self.blog)

	@staticmethod
	def get_max_id():
		return LogEntry.objects.all().aggregate(Max('id'))['id__max'] or 0
