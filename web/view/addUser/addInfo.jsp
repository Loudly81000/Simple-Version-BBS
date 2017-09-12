<!DOBTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Add User Information</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet" href="/CSSforJSP/addInfo.css" type="text/css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>
<div class="container">
    <div class="col-md-5">
        <div class="form-area">
            <form role="form" method="post" action="/UserClServlet?type=addInfo">
                <br style="clear:both">
                <h3 style="margin-bottom: 25px; text-align: center;">Sign Up</h3>
                <div class="form-group">
                    <input type="text" class="form-control" name="userName" placeholder="Name" required>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password" placeholder="Password" required>
                </div>
                <div class="form-group">
                    <input type="email" class="form-control"name="email" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <label class="radio-inline">
                        <input type="radio" name="gender"  value="male" required>Male
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="gender"  value="female"required>Female
                    </label>
                </div>
                <div class="form-group">
                    <textarea class="form-control" name="my_desc" type="textarea" placeholder="Pls introduce yourself"
                              maxlength="140" rows="7"></textarea>
                    <span class="help-block"><p id="characterLeft" class="help-block ">You have reached the limit</p></span>
                </div>
                <a href="/UserClServlet?type=gotowelcome" >return to last page</a>

                <input type="submit" id="submit" name="submit" class="btn btn-primary pull-right" value="Submit">
            </form>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
        integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>

<script>
    $(document).ready(function(){
        $('#characterLeft').text('140 characters left');
        $('#message').keydown(function () {
            var max = 140;
            var len = $(this).val().length;
            if (len >= max) {
                $('#characterLeft').text('You have reached the limit');
                $('#characterLeft').addClass('red');
                $('#btnSubmit').addClass('disabled');
            }
            else {
                var ch = max - len;
                $('#characterLeft').text(ch + ' characters left');
                $('#btnSubmit').removeClass('disabled');
                $('#characterLeft').removeClass('red');
            }
        });
    });
</script>

