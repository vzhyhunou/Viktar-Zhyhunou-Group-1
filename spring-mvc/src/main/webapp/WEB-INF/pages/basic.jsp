<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
</head>
<body>

   <div style="width: 100%; height: 50px; margin:0 auto; background:#669999;">
       <tiles:insertAttribute name="header" />
   </div>

   <div style="width: 100%; height: 100px; margin:0 auto;">
      <tiles:insertAttribute name ="body"/>
   </div>

   <div style="width: 100%; height: 50px; margin:0 auto; background:#669999;">
      <tiles:insertAttribute name="footer" />
   </div>

</body>
</html>
