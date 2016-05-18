"""webapps URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.8/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Add an import:  from blog import urls as blog_urls
    2. Add a URL to urlpatterns:  url(r'^blog/', include(blog_urls))
"""
from django.conf.urls import include, url, patterns
from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns('',
    url(r'^grumblr$', 'grumblr.views.home',name='home'),

    # route for built-in authentication with our own customer login page
    url(r'^grumblr/get_blogs$','grumblr.views.get_blogs',name="get_blog"),
    url(r'^grumblr/get_changes/(?P<max_id>\d+)$','grumblr.views.get_changes',name="get_changes"),
    url(r'^grumblr/login$','django.contrib.auth.views.login',{'template_name':'Login.html'},name="login"),
    url(r'^grumblr/logout$','django.contrib.auth.views.logout_then_login',name="logout"),
    url(r'^grumblr/register$','grumblr.views.register',name="register"),
    url(r'^grumblr/profile$','grumblr.views.profile',name="profile"),
    url(r'^grumblr/get_profile_blogs/(?P<id>\d+)$','grumblr.views.get_profile_blogs',name="get_profile_blogs"),
    url(r'^grumblr/post_blog','grumblr.views.post_blog',name="post"),
    url(r'^grumblr/add_comment/(?P<id>\d+)$','grumblr.views.add_comment',name='add_comment'),
    url(r'^grumblr/other_profile/(?P<id>\d+)$','grumblr.views.other_profile',name="other_profile"),
    url(r'^grumblr/edit_profile','grumblr.views.edit_profile',name='edit_profile'),
    url(r'^grumblr/get_photo/(?P<id>\d+)$','grumblr.views.get_photo',name="get_photo"),
    url(r'^grumblr/follower_stream$','grumblr.views.follower_stream',name="follower_stream"),
    url(r'^grumblr/get_follower_blogs$','grumblr.views.get_follower_blogs',name="get_follower_blogs"),
    url(r'^grumblr/get_follower_changes/(?P<max_id>\d+)$','grumblr.views.get_follower_changes',name="get_follower_changes"),
    url(r'^grumblr/follow/(?P<id>\d+)$','grumblr.views.follow',name="follow"),
    url(r'^grumblr/unfollow/(?P<id>\d+)$','grumblr.views.unfollow',name="unfollow"),
    url(r'^grumblr/password_change','grumblr.views.send_password_confirm',name="password_change"),
    url(r'^grumblr/confirm/(?P<token>[a-zA-Z0-9_\-]+)/(?P<username>[a-zA-Z0-9_]+)/$','grumblr.views.comfirm',name='confirm'),
    url(r'^grumblr/edit_password$','grumblr.views.edit_password',name="edit_password"),
    url(r'^admin/',include(admin.site.urls)),
)
