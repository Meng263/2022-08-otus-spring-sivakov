db = db.getSiblingDB('libraly');

db.authors.insertMany(
    [
        {
            _id: "10",
            name: "PUSHKIN"
        },
        {
            _id: "20",
            name: "LERMONTOV"
        },
        {
            _id: "100",
            name: "author_helper"
        },
    ]
);

db.genres.insertMany(
    [
        {
            _id: "10",
            name: "HORROR"
        },
        {
            _id: "20",
            name: "DRAMA"
        },
        {
            _id: "100",
            name: "genre_helper"
        },
    ]
);

db.books.insertMany(
    [
        {
            _id: "10",
            name: "RUSALKA",
            author: {
                $ref: "author",
                $id: "10"
            },
            genre: {
                $ref: "genre",
                $id: "20"
            }
        },
        {
            _id: "20",
            name: "MASQARAD",
            author: {
                $ref: "author",
                $id: "20"
            },
            genre: {
                $ref: "genre",
                $id: "20"
            }
        },
        {
            _id: "100",
            name: "book_helper",
            author: {
                $ref: "author",
                $id: "100"
            },
            genre: {
                $ref: "genre",
                $id: "100"
            }
        }
    ]
);

db.comments.insertMany([
    {
        _id: "10",
        text: "COMMENT1",
        book: {
            $ref: "book",
            $id: "10"
        }
    },
    {
        _id: "20",
        text: "COMMENT2",
        book: {
            $ref: "book",
            $id: "20"
        }
    },
    {
        _id: "30",
        text: "COMMENT3",
        book: {
            $ref: "book",
            $id: "20"
        }
    },
    {
        _id: "40",
        text: "COMMENT4",
        book: {
            $ref: "book",
            $id: "20"
        }
    },
    {
        _id: "50",
        text: "COMMENT5",
        book: {
            $ref: "book",
            $id: "100"
        }
    },
    {
        _id: "60",
        text: "COMMENT6",
        book: {
            $ref: "book",
            $id: "100"
        }
    }
]);
