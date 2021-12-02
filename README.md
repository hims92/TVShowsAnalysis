# TV Shows Analysis using Knowledge graphs
A POC for Getting insights from TV Shows data and event logs from a streaming service

## High level architecture (Simplified in actual implementation)
![image](https://user-images.githubusercontent.com/1376157/144406692-89e57657-b4fc-421b-943d-a6aef3150b96.png)

## Data pipeline details
#### Input datasets -
1) Bulk data of TV shows from rest endpoint
2) View events from kafka stream

#### Data Processing - 
##### Online processing
View events are processed by kafka consumer. I have not set up a kafka consumer for the excercise, so to mock the online processing, I am reading the raw events from a file, aggregating them in different ways as if kakfa streams would be aggregating them and saving the aggregated data back in a file.
Online processing mock code - https://github.com/hims92/TVShowsAnalysis/tree/master/src/main/java/com/tvshows/datapipeline/onlineprocessors

##### Batch processing
TV shows data is transformed into graph data via a batch ETL pipeline. Graph dataset is captured in the form of triples - subject, predicate and object, inspired from turtle format.
Aggregated user views data per country is also ingested in graph. I have not ingested other aggregations, like per day stats, in graph, but it can be done in a similar way.

Pipeline code is here - https://github.com/hims92/TVShowsAnalysis/tree/master/src/main/java/com/tvshows/datapipeline
This class orchestrates different components - https://github.com/hims92/TVShowsAnalysis/blob/master/src/main/java/com/tvshows/datapipeline/TvShowsPipeLine.java

#### Output datasets

As a result of above processing, I have generated following datasets, which can be used for analysis.
1) Graph dataset - in triples format as described above. Each run produces a new dated version which can be loaded in a graph database.

   Shows graph - https://github.com/hims92/TVShowsAnalysis/tree/master/src/main/resources/generatedGraph/Shows/20211201_154042
   
   Views graph - https://github.com/hims92/TVShowsAnalysis/tree/master/src/main/resources/generatedGraph/Views
   
   Combined merged graph - https://github.com/hims92/TVShowsAnalysis/tree/master/src/main/resources/generatedGraph/MergedGraph
   
2) Aggregated views dataset for user engagement tracking.
   https://github.com/hims92/TVShowsAnalysis/tree/master/src/main/resources/viewEvents/aggregatedViewEvents

## Data Analysis using Graphs

Data analysis would have been much easier if graph dataset was loaded in a graph database. Then I would have been able to issue SPARQL / Cypher / Gremlin queries and I would not have to do graph walks.

In absense of graph database, I am using pandas for analysis, and doing the graph walk by self joining graph dataset.
This notebook shows sample queries to answer business questions - 
https://github.com/hims92/TVShowsAnalysis/blob/master/DataAnalysisScripts/QueriesOnGraph.ipynb

The view events aggregated by online consumers could also be loaded into some database / elastic search, which would have enabled querying on user logs and having dashboards.
In this POC, I am again loading the aggregated data from files and generating stats/chart using pandas.
This notebook shows some sample queries - 
https://github.com/hims92/TVShowsAnalysis/blob/master/DataAnalysisScripts/UserEngagement.ipynb


## Results
Some sample queries which other teams can run on graph data. Data can be queried in lot of different ways, but these are few examples which come to my mind.

Most popular genres among viewers:

![image](https://user-images.githubusercontent.com/1376157/144399523-a4865946-37c8-4204-b95e-b0ad3c9812dc.png)

Top 10 shows in China

![image](https://user-images.githubusercontent.com/1376157/144399638-751a3eea-7035-4576-bc80-9e182f2a767d.png)

Least viewed shows:

![image](https://user-images.githubusercontent.com/1376157/144399436-868485a2-9841-4e3c-9d3d-d0c6f5ec10d2.png)

Daily active users:

![image](https://user-images.githubusercontent.com/1376157/144399812-ca2b54b2-8696-40f3-83f5-83bfa14773a8.png)

Views in China per day:

![image](https://user-images.githubusercontent.com/1376157/144399971-89747d5a-a783-4aaa-b26b-2831ec82e767.png)


### What can be improved given more time:
1) I could have used Apache Jena / RDF constructs to model the graph nodes/edges
2) Config for different parameters. I have currently hardcoded different file paths, which would move to a config (different for staging / pre-prod / prod etc).
3) Proper error handling and logging is missing in data pipeline.

