# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
from django.conf import settings


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('grumblr', '0013_auto_20151022_0335'),
    ]

    operations = [
        migrations.CreateModel(
            name='Comment',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('text', models.CharField(max_length=42)),
                ('time', models.DateTimeField(verbose_name=b'%Y-%m-%d %H:%M:%S')),
                ('blog', models.ForeignKey(to='grumblr.Blog')),
                ('user', models.ForeignKey(to=settings.AUTH_USER_MODEL)),
            ],
        ),
        migrations.CreateModel(
            name='LogEntry',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('op', models.CharField(max_length=4, choices=[(b'add', b'add'), (b'edit', b'edit')])),
                ('blog', models.ForeignKey(to='grumblr.Blog')),
            ],
        ),
    ]
