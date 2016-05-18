# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('grumblr', '0008_auto_20151007_0139'),
    ]

    operations = [
        migrations.AlterField(
            model_name='userinfo',
            name='pitcture',
            field=models.ImageField(default=b'photos/01.jpg', upload_to=b'photos', blank=True),
        ),
    ]
