package main

import (
	"database/sql"
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"os"

	"github.com/gorilla/mux"
	_ "github.com/lib/pq"
)

type Book struct {
	ID     int    `json:"id"`
	Title  string `json:"time_options"`
	Author string `json:"title"`
}

var db *sql.DB
var err error

func main() {

	const defaultVal = "postgres://sfuxnszcyiljgo:acb3ebb0fc5feae67f76218628b966fae51235151f4dd72e82e8489376ceeeae@ec2-54-220-218-59.eu-west-1.compute.amazonaws.com:5432/d5rbac7bvvmef7"
	connStr := os.Getenv("connStr")
	if connStr == "" {
		connStr = defaultVal
	}

	db, err = sql.Open("postgres", connStr)
	if err != nil {
		log.Fatal(err)
	}
	defer func(db *sql.DB) {
		err := db.Close()
		if err != nil {
			log.Fatal(err)
		}
	}(db)

	// Initialize router
	router := mux.NewRouter()

	// Define routes
	router.HandleFunc("/books", getBooks).Methods("GET")
	router.HandleFunc("/books/{id}", getBook).Methods("GET")
	router.HandleFunc("/books", createBook).Methods("POST")
	router.HandleFunc("/books/{id}", updateBook).Methods("PUT")
	router.HandleFunc("/books/{id}", deleteBook).Methods("DELETE")

	// Start server
	log.Fatal(http.ListenAndServe(":8080", router))
}

func getBooks(w http.ResponseWriter, _ *http.Request) {
	// Query database for all books

	rows, err := db.Query("SELECT t.* FROM public.schema_table t")
	if err != nil {
		log.Fatal(err)
	}
	defer func(rows *sql.Rows) {
		err := rows.Close()
		if err != nil {
			log.Fatal(err)
		}
	}(rows)

	// Create slice of books
	var books []Book

	// Iterate over rows and append to slice
	for rows.Next() {
		var book Book
		err := rows.Scan(&book.ID, &book.Title, &book.Author)
		if err != nil {
			log.Fatal(err)
		}
		books = append(books, book)
	}

	// Convert slice to JSON and write to response
	err = json.NewEncoder(w).Encode(books)
	if err != nil {
		log.Fatal(err)
	}
}

func getBook(w http.ResponseWriter, r *http.Request) {
	// Get book ID from request parameters
	params := mux.Vars(r)
	id := params["id"]

	// Query database for book with matching ID
	row := db.QueryRow("SELECT t.* FROM public.schema_table t WHERE id=$1", id)

	// Create new book struct and scan row values into it
	var book Book
	err := row.Scan(&book.ID, &book.Title, &book.Author)
	if err != nil {
		log.Fatal(err)
	}

	// Convert book struct to JSON and write to response
	err = json.NewEncoder(w).Encode(book)
	if err != nil {
		log.Fatal(err)
	}
}

func createBook(w http.ResponseWriter, r *http.Request) {
	// Decode JSON request body into new book struct
	var book Book
	err := json.NewDecoder(r.Body).Decode(&book)
	if err != nil {
		log.Fatal(err)
	}

	log.Println(fmt.Sprintf("create %v", book.ID))

	// Insert new book into database
	_, err = db.Exec("INSERT INTO schema_table (title, time_options) VALUES ($1, $2)",
		book.Title, book.Author)

	if err != nil {
		log.Fatal(err)
	}

	// Write success message to response
	err = json.NewEncoder(w).Encode("Book created successfully")
	if err != nil {
		log.Fatal(err)
	}
}

func updateBook(w http.ResponseWriter, r *http.Request) {
	// Get book ID from request parameters
	params := mux.Vars(r)

	// Decode JSON request body into updated book struct
	var book Book
	err := json.NewDecoder(r.Body).Decode(&book)
	if err != nil {
		log.Fatal(err)
	}

	// Update book in database
	_, err = db.Exec("UPDATE public.schema_table t SET title=$1, time_options=$2 WHERE id=$3",
		book.Title, book.Author, params[`id`])
	if err != nil {
		log.Fatal(err)
	}

	// Write success message to response
	err = json.NewEncoder(w).Encode("Book updated successfully")
	if err != nil {
		log.Fatal(err)
	}
}

func deleteBook(w http.ResponseWriter, r *http.Request) {
	// Get book ID from request parameters
	params := mux.Vars(r)
	id := params["id"]

	// Delete book from database
	_, err = db.Exec("DELETE FROM public.schema_table t WHERE id=$1", id)
	if err != nil {
		log.Fatal(err)
	}

	// Write success message to response
	err = json.NewEncoder(w).Encode("Book deleted successfully")
	if err != nil {
		log.Fatal(err)
	}
}
