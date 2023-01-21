from fastapi import FastAPI

from os.path import dirname, abspath, join
import json

app = FastAPI()


@app.get("/")
def read_root():
    with open(join(dirname(abspath(__file__)), 'uog.json')) as f:
        data = json.loads(f.read())

    return {"message": data['postcode']}
    # return {"Hello": "world"}


@app.get("/items/{item_id}")
def read_item(item_id: int, q: str = None):
    return {"item_id": item_id, "q": q}

# https://api.getAddress.io/find/{postcode}?api-key={api-key}
