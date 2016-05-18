# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
import datetime
from django.utils.timezone import utc


class Migration(migrations.Migration):

    dependencies = [
        ('grumblr', '0004_auto_20150925_0049'),
    ]

    operations = [
        migrations.AddField(
            model_name='blog',
            name='time',
            field=models.DateTimeField(default=datetime.datetime(2015, 9, 25, 3, 32, 21, 668638, tzinfo=utc), verbose_name=b'%Y-%m-%d %H:%M:%S'),
            preserve_default=False,
        ),
    ]
