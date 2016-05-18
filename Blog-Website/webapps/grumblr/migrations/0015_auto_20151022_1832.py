# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('grumblr', '0014_comment_logentry'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='comment',
            name='user',
        ),
        migrations.AddField(
            model_name='comment',
            name='user_info',
            field=models.ForeignKey(default=1, to='grumblr.UserInfo'),
            preserve_default=False,
        ),
    ]
