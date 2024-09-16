<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>排名</th>
        <th>商品名</th>
        <th>成交金额</th>
    </tr>
    </thead>
    <tbody>
    <#list goods as g>
        <tr>
            <td>${g_index+1}</td>
            <td>${g.name!''}</td>
            <td>${g.price!'0'}</td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>
