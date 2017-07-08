# -*- coding:utf8 -*-
# 暴躁的肥肥 不需要注释
TEXT = 'emoji.txt'
import codecs

def a():
	print('AliCli 对瓶吹')
	emoji = guaiguai()
	zhanghao(emoji)
	print('All Done! 快来食我大雕啦~')

def guaiguai():
	emoji_l = open(TEXT,encoding="utf8").readlines()
	emoji = []
	for i in range(0, len(emoji_l)):
		emoji.append(emoji_l[i].strip('\ufeff').strip('\n'))

	return emoji

def zhanghao(emoji):
	output = open('adaoemoji.txt', 'a', encoding='utf-8')
	output.write('# Adao nimingban emoji for Google Pinyin Input' + '\n')

	for i in range(0,len(emoji)):
		print(i)
		output.write(emoji[i] + '	' + str(i + 1) + '	' + 'ad' + '\n')

a()

