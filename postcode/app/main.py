from fastapi import FastAPI

import json
import queue
from os.path import dirname, abspath, join

app = FastAPI()


@app.get("/")
def read_root():

    # https://api.getAddress.io/find/{postcode}?api-key={api-key}
    with open(join(dirname(abspath(__file__)), 'api_data/aparto.json')) as f:
        data = json.loads(f.read())

    order = ["line_4", "line_3", "line_2", "line_1", ]

    lines_tree = {}

    for addr in data["addresses"]:
        que = list(filter(lambda line: line,
                          [addr[key] for key in order]))

        curr = lines_tree

        for line in que:
            if line not in curr:
                curr[line] = {}
            curr = curr[line]

    que = queue.Queue()
    que.put(lines_tree)

    while not que.empty():
        curr = que.get()
        for key in curr.keys():
            if not curr[key]:
                curr[key] = ''
                continue
            que.put(curr[key])

    return lines_tree


@app.get("/items/{item_id}")
def read_item(item_id: int, q: str = None):
    return {"item_id": item_id, "q": q}


