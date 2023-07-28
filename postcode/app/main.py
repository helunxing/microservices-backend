import os
import json
import queue
import random

from fastapi import FastAPI
from fastapi.responses import Response
from loguru import logger
from os.path import dirname, abspath, join

default_val = "default"
env_val = os.getenv("ENV_VAR")
val = env_val if env_val else default_val

app = FastAPI()


@app.get("/")
def running_notice():
    return Response(content="postcode microservice running", media_type="text/plain")


@app.get("/{postcode}")
async def read_root(postcode, secret_key=''):
    # raw data is from:
    # https://api.getAddress.io/find/{postcode}?api-key={api-key}

    data_folder = os.path.join(dirname(abspath(__file__)), "api_data")
    files = os.listdir(data_folder)
    select_file = random.choice(files)
    logger.info("select_file: {}".format(select_file))

    with open(os.path.join(data_folder, select_file)) as f:
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
        for secret_key in curr.keys():
            if not curr[secret_key]:
                curr[secret_key] = None
                continue
            que.put(curr[secret_key])

    return lines_tree
