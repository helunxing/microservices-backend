package main

import (
	"encoding/json"
	"log"
	"math/rand"
	"net/http"
	"os"
	"path/filepath"
	"strings"
	"time"

	"github.com/gorilla/mux"
)

var err error

func main() {
	router := mux.NewRouter()
	router.HandleFunc("/", hello).Methods("GET")
	router.HandleFunc("/{postcode}", handlePostcode).Methods("GET")
	log.Fatal(http.ListenAndServe(":8020", router))
}

func hello(writer http.ResponseWriter, _ *http.Request) {
	err = json.NewEncoder(writer).Encode("Book created successfully")
	if err != nil {
		log.Fatal(err)
	}
}

func handlePostcode(writer http.ResponseWriter, request *http.Request) {
	vars := mux.Vars(request)
	postcode := vars["postcode"]

	dataFolder, _ := filepath.Abs(filepath.Join(".", "data"))
	files, _ := os.ReadDir(dataFolder)
	randomNumber := rand.New(rand.NewSource(time.Now().UnixNano())).Intn(len(files))
	selectFile := files[randomNumber].Name()
	log.Printf("inquire postcode: %s select_file: %s\n", postcode, selectFile)

	dataBytes, _ := os.ReadFile(filepath.Join(dataFolder, selectFile))
	var data map[string]interface{}

	err = json.Unmarshal(dataBytes, &data)
	if err != nil {
		log.Fatal(err)
	}

	order := []string{"line_4", "line_3", "line_2", "line_1"}

	linesTree := make(map[string]interface{})

	for _, addrInterface := range data["addresses"].([]interface{}) {
		addr := addrInterface.(map[string]interface{})
		var que []string
		for _, key := range order {
			if line, ok := addr[key].(string); ok && line != "" {
				que = append(que, line)
			}
		}

		curr := linesTree

		for _, line := range que {
			if _, ok := curr[line]; !ok {
				curr[line] = make(map[string]interface{})
			}
			curr = curr[line].(map[string]interface{})
		}
	}

	var que []map[string]interface{}
	que = append(que, linesTree)

	for len(que) > 0 {
		curr := que[0]
		que = que[1:]
		for secretKey, value := range curr {
			if value == nil {
				curr[secretKey] = nil
				continue
			}
			if valueMap, ok := value.(map[string]interface{}); ok {
				que = append(que, valueMap)
			}
		}
	}

	rawBytes, _ := json.Marshal(linesTree)
	str := string(rawBytes)

	_, err = writer.Write([]byte(strings.Replace(str, "{}", "null", -1)))
	if err != nil {
		log.Fatal(err)
	}
}
