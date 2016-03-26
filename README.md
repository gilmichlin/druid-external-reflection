# Druid Demo of reflection based filters and extractions

### request

```json
{
  "queryType": "topN",
  "dataSource": "wikipedia",
  "intervals": "2013-08-01T00:00:00.000Z/2013-08-02T00:00:00.000Z",
  "granularity": "all",
  "context": {
    "timeout": 60000
  },
  "filter": {
    "type": "reflection",
    "className": "com.bla.filter.StartsWithExternalReflectionFilter",
    "constructorParams": ["user","A"]
  },
  "dimension":{
  "type" : "extraction",
  "dimension" : "user",
  "outputName" :  "second_letter",
  "extractionFn" : {
    "type" : "reflection",
     "className": "com.bla.extraction.SubstringExternalReflectionExtraction",
     "constructorParams": ["1","2"]
  }
},
  "aggregations": [
    {
      "name": "cnt",
      "type": "count"
    }
  ],
  "metric": "cnt",
  "threshold": 10
}
```

### response

```json
[
  {
    "timestamp": "2013-08-01T00:00:00.000Z",
    "result": [
      {
        "cnt": 29365,
        "second_letter": "d"
      },
      {
        "cnt": 5339,
        "second_letter": "t"
      },
      {
        "cnt": 1251,
        "second_letter": "r"
      },
      {
        "cnt": 854,
        "second_letter": "v"
      },
      {
        "cnt": 759,
        "second_letter": "A"
      },
      {
        "cnt": 648,
        "second_letter": "n"
      },
      {
        "cnt": 235,
        "second_letter": "i"
      },
      {
        "cnt": 230,
        "second_letter": "e"
      },
      {
        "cnt": 220,
        "second_letter": "n"
      },
      {
        "cnt": 216,
        "second_letter": "P"
      }
    ]
  }
]
```