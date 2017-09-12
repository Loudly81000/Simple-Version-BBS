<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 9/10/2017
  Time: 6:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/CSSforJSP/manager.css" type="text/css"/>
    <style>
        @import url(http://netdna.bootstrapcdn.com/font-awesome/3.0.0/css/font-awesome.min.css);
        body{margin-top:20px;}
        .fa-fw {width: 2em;}
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <ul class="nav nav-pills nav-stacked admin-menu">
                <li><a href="http://www.jquery2dotnet.com" data-target-id="home"><i class="fa fa-home fa-fw"></i>Home</a></li>
                <li><a href="http://www.jquery2dotnet.com" data-target-id="pages"><i class="fa fa-file-o fa-fw"></i>Message Board</a></li>
                <li><a href="http://www.jquery2dotnet.com" data-target-id="forms"><i class="fa fa-tasks fa-fw"></i>Write Comment</a></li>
                <li><a href="http://www.jquery2dotnet.com" data-target-id="table"><i class="fa fa-table fa-fw"></i>Edit Comment</a></li>
            </ul>
        </div>
        <div class="col-md-9 well admin-content" id="home">
            <p>
                Hello! Welcome to Message Board.<br>
                You can click on  "Write Msg" tag , and leave message in here.
                In "Edit Msg", edit message you leaved.<br>
                Admin personal information in "Your Info"
            </p>
        </div>
        <div class="col-md-9 well admin-content" id="pages">
            <div class="row">
                <div class="col-sm-6">
                    <div id="tb-testimonial" class="testimonial testimonial-default">
                        <div class="testimonial-section">
                            Denim you probably haven't heard of. Lorem ipsum dolor met consectetur adipisicing sit amet, consectetur adipisicing elit, of them jean shorts sed magna aliqua. Lorem ipsum dolor met.
                        </div>
                        <div class="testimonial-desc">
                            <img src="https://placeholdit.imgix.net/~text?txtsize=9&txt=100%C3%97100&w=100&h=100" alt="" />
                            <div class="testimonial-writer">
                                <div class="testimonial-writer-name">Zahed Kamal</div>
                                <div class="testimonial-writer-designation">Front End Developer</div>
                                <a href="#" class="testimonial-writer-company">Touch Base Inc</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-9 well admin-content" id="forms">
            Forms
        </div>
        <div class="col-md-9 well admin-content" id="table">
            Table
        </div>
    </div>
</div>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
</body>
</html>
<script>
    $(document).ready(function()
    {
        var navItems = $('.admin-menu li > a');
        var navListItems = $('.admin-menu li');
        var allWells = $('.admin-content');
        var allWellsExceptFirst = $('.admin-content:not(:first)');

        allWellsExceptFirst.hide();
        navItems.click(function(e)
        {
            e.preventDefault();
            navListItems.removeClass('active');
            $(this).closest('li').addClass('active');

            allWells.hide();
            var target = $(this).attr('data-target-id');
            $('#' + target).show();
        });
    });
</script>
