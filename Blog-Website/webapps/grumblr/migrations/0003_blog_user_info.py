# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('grumblr', '0002_userinfo'),
    ]

    operations = [
        migrations.AddField(
            model_name='blog',
            name='user_info',
            field=models.ForeignKey(default=1, to='grumblr.UserInfo'),
            preserve_default=False,
        ),
    ]
