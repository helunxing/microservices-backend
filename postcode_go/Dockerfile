FROM golang:1.20.7

WORKDIR /app

COPY go.mod ./
COPY *.go ./
COPY data ./data

RUN go mod download
RUN go get postcode_go
RUN go build -o /docker-gs-ping

EXPOSE 8020

CMD [ "/docker-gs-ping" ]
