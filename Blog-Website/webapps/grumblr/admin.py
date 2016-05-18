from django.contrib import admin
from grumblr.models import UserInfo
from grumblr.models import Blog

# Register your models here.
admin.site.register(Blog)
admin.site.register(UserInfo)