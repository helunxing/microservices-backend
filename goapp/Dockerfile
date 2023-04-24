FROM golang:1.17-alpine

WORKDIR /app

COPY go.mod ./
# COPY go.sum ./
COPY *.go ./

RUN go mod download

RUN go get goapp

RUN go build -o /docker-gs-ping

EXPOSE 8000

CMD [ "/docker-gs-ping" ]
