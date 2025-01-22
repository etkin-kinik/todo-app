ToDo application'i icin task Entity'si olusturulmustur. Bu Entity'nin icerisinde:

-title
-completed
-priority
-createdDate
-lastModifiedDate
-completedDate

fieldlari bulunmaktadir.

Istediklerinize ek olarak bir de filter logic eklenmistir. Filter logic priority fieldini referans almaktadir. URL'inizde http://localhost:8080/api/tasks?filter=high yazdiniz zaman size sadece high priority tasklari return olacaktir. Eger ki bir taski create'lerken priority'i belirtmezseniz, default olarak "Low" value recorda kaydedilecektir.

