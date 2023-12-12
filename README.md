# queryboot

MyBatis Log to SQL Query Spring Boot Process.

ex)
<pre>
at Tomcat Log
  
[YYYY-MM-DD HH:MM:SS] [DEBUG] - ==> SELECT * FROM TBL_HISTORY WHERE SND_DATE BETWEEN ? AND ?
[YYYY-MM-DD HH:MM:SS] [DEBUG] - ==> Parameters: 20231212000000(String), 20231212235959(String)

Copy this 2 rows (Query, Parameters) and click 'Combine' button !
And this is result

SELECT * FROM TBL_HISTORY WHERE SND_DATE BETWEEN '20231112000000' AND '20231212235959'

You can use at [(String), (Integer), (Long)] Parameters
</pre>
