# -*- coding: utf-8 -*-
import sys
from gensim.summarization.summarizer import summarize

text = sys.argv[1]

print(summarize(text, ratio=0.1))
