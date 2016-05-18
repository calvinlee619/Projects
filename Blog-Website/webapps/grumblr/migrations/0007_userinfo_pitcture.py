# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('grumblr', '0006_auto_20151005_0245'),
    ]

    operations = [
        migrations.AddField(
            model_name='userinfo',
            name='pitcture',
            field=models.ImageField(upload_to=b'photos', blank=True),
        ),
    ]
