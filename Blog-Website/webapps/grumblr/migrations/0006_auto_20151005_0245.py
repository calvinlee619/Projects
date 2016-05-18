# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('grumblr', '0005_blog_time'),
    ]

    operations = [
        migrations.AddField(
            model_name='userinfo',
            name='age',
            field=models.CharField(default=b'', max_length=5, blank=True),
        ),
        migrations.AddField(
            model_name='userinfo',
            name='short_blog',
            field=models.CharField(default=b'', max_length=420, blank=True),
        ),
    ]
