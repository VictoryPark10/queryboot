# queryboot

MyBatis and Hibernate Log to SQL Query Spring Boot Process.

1. MyBatis

<pre>
at Spring Log
  
[YYYY-MM-DD HH:MM:SS] [DEBUG] - ==> SELECT * FROM TBL_HISTORY WHERE SND_DATE BETWEEN ? AND ?
[YYYY-MM-DD HH:MM:SS] [DEBUG] - ==> Parameters: 20231212000000(String), 20231212235959(String)

Copy this 2 lines (Query, Parameters) and click 'Combine MyBatis' button !
And this is result

SELECT * FROM TBL_HISTORY WHERE SND_DATE BETWEEN '20231112000000' AND '20231212235959'

You can use at [(String), (Integer), (Long)] Parameters
</pre>


2. Hibernate

<pre>

at Spring Log (spring.jpa.properties.hibernate.use_sql_comment=off, logging.level.org.hibernate.type=trace)


Hibernate: select A.reg\_date from TBL\_HELLO where reg\_date between ? and ?

2024-05-30 11:09:00.000 TRACE 12345 --- [nio-8080-exec-3] o.h.type.descriptor.sql.BasicBinder : binding parameter [1] as [VARCHAR] - [2024-05-01]

2024-05-30 11:09:00.001 TRACE 12345 --- [nio-8080-exec-3] o.h.type.descriptor.sql.BasicBinder : binding parameter [2] as [VARCHAR] - [2024-05-30]


Copy this 3 lines (Query, Parameters) and click 'Combine Hibernate' button !

And this is result


select A.reg_date from TBL_HELLO where reg_date between '2024-05-01' and '2024-05-30'


You can use at ['VARCHAR', 'CHAR', 'DOUBLE', 'BIGINT', 'BOOLEAN', etc...] Parameters

</pre>
