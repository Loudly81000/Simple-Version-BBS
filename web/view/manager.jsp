<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 9/10/2017
  Time: 6:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/CSSforJSP/manager.css" type="text/css"/>
    <style>
        @import url(http://netdna.bootstrapcdn.com/font-awesome/3.0.0/css/font-awesome.min.css);
        body{margin-top:20px;}
        .fa-fw {width: 2em;}
        .testimonial-section {
            width: 200%;}
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <ul class="nav nav-pills nav-stacked admin-menu">
                <%--index for board--%>
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
        <%--message board--%>
        <div class="col-md-9 well admin-content" id="pages">
            <div class="row">
                <div class="col-sm-6">
                    <div id="tb-testimonial" class="testimonial testimonial-default">
                        <c:forEach var="editPost" items="${showPostArrayList}">
                            <div class="testimonial-section">
                                <h4>${editPost.post_title}</h4>
                                    ${editPost.post_desc}
                            </div>
                            <div class="testimonial-desc">
                                <img src="https://placeholdit.imgix.net/~text?txtsize=9&txt=100%C3%97100&w=100&h=100" alt="" />
                                <div class="testimonial-writer">
                                    <div class="testimonial-writer-name">${editPost.userName}</div>
                                    <div class="testimonial-writer-designation"><c:if test="${editPost.gender}">
                                        Male
                                        </c:if>
                                        <c:if test="${!editPost.gender}">
                                        female
                                        </c:if></div>
                                    <a href="#" class="testimonial-writer-company">${editPost.post_time}</a>
                                </div>
                            </div><br>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <%--write comment--%>
        <div class="col-md-9 well admin-content" id="forms">
            <div class="row">
                <div class="col-md-4">
                    <img src="https://cdn1.iconfinder.com/data/icons/softwaredemo/PNG/256x256/Pencil3.png" class="img-responsive center-block" alt="">
                </div><!--.col -->
                <div class="col-md-6">
                    <h3>
                        Write Commenct
                    </h3>

                    Please write title&comment in following space.

                    <!-- NOTE: TB3 form width default is 100%, so they expan to your <div> -->
                    <form name="frmComment" id="frmComment" action="/UserClServlet?type=insertPost" method="post" >
                        <label for="txtComment">Please input title here:</label>
                        <input type="text" name="post_title" id="txtComment" class="form-control" placeholder="Title:" >
                        <br>
                        <p>
                            Write a comment
                        </p>

                        <textarea id="txtCommentHere" class="form-control" rows="3" name="post_desc" ></textarea>
                        <hr>
                        <%--<button type="button" class="btn btn-success" value="Submit" onclick="writeComment()">Submit</button>--%>
                        <input type="submit" class="btn btn-success" value="Submit" id="submit">
                        <input type="hidden" name="userName" value="${userName}" >
                    </form>

                    <hr>
                    <!--Mr.M. logo -->
                    <p>
                        <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAADqUlEQVR4nO2aTYgURxTHGzREQ1hEw7KoGMiKIouZ7nqtQ+9013srunGmu+vVGhoRFMVAPi5CLiEHnZshl5wCibDgQchx8eDJi2cvggcJogiBRSWgAQ0q+NU5rCPrOP01LGSnu//wTjP86PebrqpXMIbjROtN0mYVy3Gi9YZJ2gTkuIplkjZrAbWAWkAtoBbwfz9ILaAWULyE5D8B+RTQnCtIHQTkrpDqn6oImDfa7Q+NvuxsHdgMnrpaagECw+sAX3/Q33wvu/Z1PgWpHpVWgO2GR5Ka78Xy9K+lFbDH/fKzLAEm6ROlFSBIbc98A1z/ZJkFHM0SABj+VloBgOrGoBPgbfP72pMC+d8SC+DYQr4AEH7U3/zSCaCv5eWMrABAjgWq2zbqH/YQ7wfS2kL9E0j1uAhjpAWsRNUCVoUAqZ4I5L+KFiC/fJ+nngnJr0ZKgEn6RNaxNnC3J/65nyVIHZyi6OPP0fdsj78HyX8IqW+tXgEyvENEa4cRQETrljdnIV8Y9D2T9IZVK8By/ZPDNN/LbtmRQqrXQvLfe/fObRoxAeqFRcFpIP4xT1muPjaoQfDU70IGh5MkrWIBxUqguj2ww5SpsFQCANWztEYrIIBjoPCTSgswSZuVFiAwCCstADz1XaUF2B6frbSApGmvMgJAqiuVFpA4DKUEnM7u0ggAyU8LNY/+DEh+WB4ByHHSpac/tgy/AVQv0lgjKSDPMNSY8b/Iw7LcoDFyAho0F2S/+nwqt8xRE2BR8G2mAE//kltAkw5ttYjPjUrBjO9nr3/9VR5Wkw5tzWLVqVOnTp06hmE0m+0xcMNtK8mcnJ4dn5yeHV9JJrjhtmazPbYisCmiCUCeB1Q3hVSv3wwQD0DyZbsVdIZhWm7QsKVeAOTFZUPJoi31guUGjWGYdivogOTLgPwAkOOlZ1U3AXl+imhiGKYhSB1Nu0kBcmxjeD6v7SiK1gByFyQ/T2QufdaNomhNHmaz2R6zMTyfOvFJfpjnbzXvxGr5qsB4ejEPE5C7BZjdnMyLuUfolq9yNe840UZAdb/QjC7V8TSm5QaN1F9+wJuQtRxAquPF7hHqvuNEG7OtevpM0QuKkOpuGvPNmi/EtKVeSGMKqe4WZYKnz2QKEBheKgxGjnc4wZZEqe9ueHlrMYm3wwm2DPOMAsNLmQJA8r1h4ElrbHJ6dnwYHiDHSUdkwT1q+dK6VwvISr0Eqr4JVv4YNIyKD0K9VHoU7qXSl6H+lPU6/B+3ehOjykS6TAAAAABJRU5ErkJggg==" alt="by Mr.M." title="by Mr. M.">
                    </p>
                    <hr>
                </div><!--.col -->
            </div><!--./row -->
        </div>
        <div class="col-md-9 well admin-content" id="table">
            <div class="row">
                <div class="col-sm-6">
                    <div id="tb-testimonial2" class="testimonial testimonial-default"><%int i=1;%>
                        <c:forEach var="editPost"  items="${editPostArrayList}">
                            <div class="testimonial-section">
                                <form action="" method="post">
                                <h4>${editPost.post_title}</h4>
                                <textarea  name="post_desc"  rows="3" readonly="readonly" id='post<%++i;%>'>${editPost.post_desc}</textarea>
                                </form>
                            </div>
                            <div class="testimonial-desc">
                                <img src="https://placeholdit.imgix.net/~text?txtsize=9&txt=100%C3%97100&w=100&h=100" alt="" />
                                <div class="testimonial-writer">
                                    <div class="testimonial-writer-name">${editPost.userName}</div>
                                    <div class="testimonial-writer-designation"><c:if test="${editPost.gender}">
                                        Male
                                    </c:if>
                                        <c:if test="${!editPost.gender}">
                                            female
                                        </c:if></div>
                                    <a class="testimonial-writer-company" >${editPost.post_time}</a>
                                        <button type="button" class="btn btn-primary"
                                                style="margin-left: 50px" onclick="function post() {
                                          document.getElementById('post<%Integer.toString(i);%>').removeAttribute('readonly')
                                        }">Edit</button>
                                </div>
                            </div><br>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
</body>
</html>
<script>
    //create menu
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

//    document.getElementById("edit").onclick = function(){
//        document.getElementById("post").removeAttribute("readonly")
//    }

        //        $( "form" ).submit(function( event ) {
//
//            var post_title = document.getElementById("txtComment");
//            var post_desc = document.getElementById("txtCommentHere")
//
//          if(document.post_title.value == ""){
//            alert("please enter title")
//              $('#txtComment').focus();
//              return false
//          }
//        if(document.post_desc.value == ""){
//            alert("please enter message")
//            $('#txtCommentHere').focus();
//            return false
//        }
//
//            document.getElementById("frmComment").submit();
//
//        });


//        $("form").submit(function(){
//            alert("Submitted");
//        });

//if servlet pass write comment result, receive it and
//go to write comment page
        <%--var result = <%=session.getAttribute("result")%>--%>
        <%--if(result=="success"){--%>
        <%--var success = $(navItems).attr('forms');--%>
        <%--$('#' + success).show();--%>
        <%--alert("posted successfully");--%>
        <%--}--%>
        <%--if(result=="fail"){--%>
        <%--var fail = $(navItems).attr('forms');--%>
        <%--$('#' + fail).show();--%>
        <%--alert("posted unsuccessfully");--%>
        <%--}--%>



    //    function writeComment(e){
    //
    //
    //        var post_title = document.getElementById("txtComment");
    //        var post_desc = document.getElementById("txtCommentHere")
    //
    //        if(document.post_title.value == ""){
    //            alert("please enter title")
    //            $('#txtComment').focus();
    //            return false
    //        }
    //        else if(document.post_desc.value == ""){
    //            alert("please enter message")
    //            $('#txtCommentHere').focus();
    //            return false
    //        }else {
    //            document.getElementById("frmComment").submit();
    //        }
    //    }

    //show alert if user doesnt' enter message or title
    //    $(function(){
    //        var fsubmit = false;
    //
    //        var post_title = document.getElementById("txtComment");
    //        var post_desc = document.getElementById("txtCommentHere")
    //
    //        if(document.post_title.value == ""){
    //            alert("please enter title")
    //            $('#txtComment').focus();
    //            fsubmit = false
    //        }
    //
    //        if(document.post_desc.value == ""){
    //            alert("please enter message")
    //            $('#txtCommentHere').focus();
    //            fsubmit = false
    //        }
    //
    //        $('form').submit(function(e){
    //            return !fsubmit;
    //        });
    //
    //    });

</script>


