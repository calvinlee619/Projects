# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('grumblr', '0015_auto_20151022_1832'),
    ]

    operations = [
        migrations.AlterField(
            model_name='blog',
            name='user_info',
            field=models.ForeignKey(to='grumblr.UserInfo', null=True),
        ),
    ]
