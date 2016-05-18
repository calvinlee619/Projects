# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
from django.conf import settings


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('grumblr', '0007_userinfo_pitcture'),
    ]

    operations = [
        migrations.AddField(
            model_name='userinfo',
            name='followers',
            field=models.ManyToManyField(related_name='UserInfo_followers', to=settings.AUTH_USER_MODEL, blank=True),
        ),
        migrations.AlterField(
            model_name='userinfo',
            name='user',
            field=models.ForeignKey(related_name='UserInfo_user', to=settings.AUTH_USER_MODEL),
        ),
    ]
