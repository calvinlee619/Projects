from django import forms
from django.contrib.auth.models import User
from django.forms import Textarea
from django.forms import TextInput
from grumblr.models import *

class RegisterForm(forms.Form):
    username = forms.CharField(max_length=40,
                               label = "Username")
    first_name= forms.CharField(max_length =40,
                                label="First Name")
    last_name = forms.CharField(max_length=40,
                                label="Last Name")
    email = forms.CharField(max_length=40)
    password1  = forms.CharField(max_length=200,
                                 label="Password",
                                 widget=forms.PasswordInput)
    password2 = forms.CharField(max_length=200,
                                label="Password Comfirmation",
                                widget=forms.PasswordInput)

    def clean(self):
        cleaned_data = super(RegisterForm, self).clean()

        password1= cleaned_data.get('password1')
        password2 = cleaned_data.get('password2')

        if password1 != password2:
            raise forms.ValidationError("Password did not match")

        return cleaned_data

    def clean_username(self):
        username = self.cleaned_data.get('username')

        if User.objects.filter(username = username):
            raise forms.ValidationError("The username has already been taken")

        return username

class UserForm(forms.ModelForm):

    class Meta:
        model = UserInfo
        exclude = ('user','followers',)
        # fields = ['first_name','last_name','age','picture','short_blog']

        widgets = {'picture':forms.FileInput(attrs={'class':"form-control btn"}),
                   'first_name':forms.TextInput(attrs={'class':"form-control"}),
                   'last_name':TextInput(attrs={'class':"form-control"}),
                   'age':forms.NumberInput(attrs={'class':"form-control"}),
                   'short_blog':Textarea(attrs={'class':"form-control"}),
                   }


class PasswordChangeForm(forms.Form):
    username = forms.CharField(max_length=42)

    def clean(self):
        cleaned_data = super(PasswordChangeForm,self).clean()

        if not User.objects.filter(username = cleaned_data.get('username')):
            raise forms.ValidationError("The username you input is not valid")

        return cleaned_data


class ResetPasswordForm(forms.Form):
    password1 = forms.CharField(max_length=40,label="Password",
                                widget=forms.PasswordInput(attrs={'class':"form-control"}))
    password2 = forms.CharField(max_length=40,label="Comfirm Password",
                                widget=forms.PasswordInput(attrs={'class':"form-control"}))

    def clean(self):
        cleaned_data = super(ResetPasswordForm,self).clean()
        password1= cleaned_data.get('password1')
        password2 = cleaned_data.get('password2')

        if password1 != password2:
            raise forms.ValidationError("Password did not match")

        return cleaned_data


class BlogForm(forms.Form):
    message = forms.CharField(max_length=42)



class CommentForm(forms.Form):
    message = forms.CharField(max_length=42)

