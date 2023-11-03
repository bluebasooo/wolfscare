db.createCollection(
    "users",
    {
        validator: {
            $jsonSchema: {
                "description": "Collection for store users",
                "type": "object",
                "properties": {
                    "_id": {"type": "string"},
                    "firstname": {"type": "string"},
                    "lastname": {"type": "string"},
                    "seenMovies": {
                        "type": "array",
                        "items": {
                            "description": "Movies that user seen"
                            "type": "object",
                            "properties": {
                                "name": {"type": "string"},
                                "cameOut": {"type": "date"},
                                "genres": {
                                    "type": "array",
                                    "items": {
                                        "description": "Genres which have film",
                                        "type": "string"
                                        "minItems": 1,
                                        "uniqueItems": true
                                    }
                                }
                            }
                            "required": [
                                "name",
                                "cameOut"
                            ]
                        }
                    },
                    "required": [
                        "_id",
                        "seenMovies"
                    ]
                }
            }
        }
    }
)