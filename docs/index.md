---
layout: home
links:
    GitHub: https://github.com/cufyx/support
    Jitpack: https://jitpack.io/#com.github.cufyx/support
    Javadoc: javadoc
    YouTube: https://www.youtube.com/playlist?list=PL4GvMdlkZJ6YSYy6gERWHuqmK71LtevTd
---

{{ site.description }}

<br>

{% assign pages = site.pages | sort: 'index' %}
{% for sub in pages%}

<a class="big_candy" href="{{sub.url}}">{{sub.title}} {% if sub.beta %}<font color="red">*</font>{% endif %}</a>

<div>
{% for link in sub.links %}
<a class="small_candy" href="{{ link[1] }}">{{ link[0] }}</a>
{% endfor %}
</div>
---

{{ sub.description }}
<br>
<br>
{% endfor %}