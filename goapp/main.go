package main

import (
	"database/sql"
	"fmt"
	_ "github.com/lib/pq"
	"log"
	"os"
)

func main() {

	const defaultVal = "host=ec2-54-220-218-59.eu-west-1.compute.amazonaws.com user=sfuxnszcyiljgo password=acb3ebb0fc5feae67f76218628b966fae51235151f4dd72e82e8489376ceeeae dbname=d5rbac7bvvmef7 port=5432"
	envVal := os.Getenv("connStr")
	connStr := envVal
	if connStr == "" {
		connStr = defaultVal
	}

	db, err := sql.Open("postgres", connStr)
	if err != nil {
		panic(err)
	}
	defer db.Close()

	rows, err := db.Query("SELECT t.id, t.time_options FROM public.schema_table t")
	if err != nil {
		log.Fatal(err)
	}
	defer rows.Close()

	for rows.Next() {
		var col1 int
		var col2 string
		if err := rows.Scan(&col1, &col2); err != nil {
			log.Fatal(err)
		}
		fmt.Println(col1)
		fmt.Println(col2)
	}

	//http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
	//	w.Header().Set("Content-Type", "application/json")
	//	_, err := fmt.Fprintf(w, `{"message": "Hello World!"}`)
	//	if err != nil {
	//		return
	//	}
	//	fmt.Println("output once")
	//})
	//
	//log.Fatal(http.ListenAndServe(":8080", nil))
}
