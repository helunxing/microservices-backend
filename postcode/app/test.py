import json

from os.path import dirname, abspath, join

print(abspath(__file__))
print(dirname(abspath(__file__)))
print(join(dirname(abspath(__file__)), 'uog.json'))
