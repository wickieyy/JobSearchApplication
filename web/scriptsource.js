$(document).ready(function(){
    $("#postJobsForm").hide();
    $("#applyJobsForm").hide();
    $("#postNewJobsButton").click(function(){
      $("#postJobsForm").show();
      $("#employerHomeBody").css("opacity","0.5");
    });
  });
  $(document).ready(function(){
    $("#closeForm").click(function(){
      $("#postJobsForm").hide();
      document.getElementById("postNewJobForm").reset();
      $("#employerHomeBody").css("opacity","1");
    });
  }); 
  var PostedJobJson;
  var AvailableJobJson;
function loadPostedJobs(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            // document.getElementById("postNewJobForm").reset();
            PostedJobJson=JSON.parse(xhttp.responseText);
            var jsonSize=PostedJobJson["size"];
            if(jsonSize==0) $("#postedJobsCards").prepend("<h5 id='postedJobsHead'>No Jobs Currently Available</h5>");
            else
            { 
                for(var i=0;i<jsonSize;i++){
                    var postedJobsCard='<div class="card">'+
                                            '<div class="card-body">'+
                                                '<h4 class="card-title"><span class="postedPageCardTitle"></span><span><small class="postedPageCardTitleLocation"></small></span><span><button class="btn btn-success btn-sm" onclick="togglePostedJobStatus('+i+')"><span class="statusButton"></span>&nbsp;&#9660;</button></span></h4>'+
                                                '<div class="postedJobsCardBody">'+
                                                    '<span class="postedPageCardPost">&emsp;Vacancies in Post : </span>'+
                                                    '&emsp;&emsp;<span class="postedPageDepartment">Department : </span>'+
                                                    '&emsp;&emsp;<span class="postedPageRequiredExperience">Expected Experience : </span><br><br>'+
                                                    '<span class="postedPageRequiredSkills">&emsp;Expected Skills : </span><span class="numberOfApplications">Number of Applied candidates : </span><br>'+
                                                    '<br><small><span class="postedPagePostedBy">posted by : </span></small>'+
                                                '</div>'+
                                            '</div> '+
                                        '</div><br>';                 
                    $("#postedJobsCards").append(postedJobsCard);
                    $(".postedPageCardTitle")[i].append(PostedJobJson["companyName"+i]+", ");
                    $(".postedPageCardTitleLocation")[i].append(PostedJobJson["location"+i]);
                    $(".statusButton")[i].append(PostedJobJson["vacancyStatus"+i]);
                    $(".postedPageCardPost")[i].append(PostedJobJson["post"+i]);
                    $(".postedPageDepartment")[i].append(PostedJobJson["department"+i]);
                    $(".postedPageRequiredExperience")[i].append(PostedJobJson["requiredExperience"+i]+" year(s)");
                    $(".postedPageRequiredSkills")[i].append(PostedJobJson["skills"+i]);
                    $(".postedPagePostedBy")[i].append(PostedJobJson["postedBy"+i]);
                    var statusButton = document.getElementsByClassName("statusButton");
                    if(statusButton[i].innerHTML=="Open") statusButton[i].parentNode.setAttribute("class","btn btn-success btn-sm");
                    if(statusButton[i].innerHTML=="Closed")  statusButton[i].parentNode.setAttribute("class","btn btn-danger btn-sm");
                }
                $("#postedJobsCards").prepend("<h3 id='postedJobsHead'>Posted Jobs</h3>");
            }
        }
    }
    xhttp.open("GET","PostedJobs",true);
    xhttp.send();
}
function loadPostedJobsForSeekers(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            AvailableJobJson=JSON.parse(xhttp.responseText);
            var jsonSize=AvailableJobJson["size"];
            if(jsonSize==0) $("#postedJobsCards").prepend("<h5 id='postedJobsHead'>No Jobs Currently Available</h5>");
            else
            { 
                for(var i=0;i<jsonSize;i++){
                    var applicationsCount = 0;
                    var postedJobsCard='<div class="card">'+
                                            '<div class="card-body">'+
                                                '<h4 class="card-title"><span class="postedPageCardTitle"></span><span><small class="postedPageCardTitleLocation"></small></span><span class="badge badge-success statusFlag"></span></h4>'+
                                                '<div class="postedJobsCardBody">'+
                                                    '<span class="postedPageCardPost">&emsp;Vacancies in Post : </span>'+
                                                    '&emsp;&emsp;<span class="postedPageDepartment">Department : </span>'+
                                                    '&emsp;&emsp;<span class="postedPageRequiredExperience">Expected Experience : </span><br><br>'+
                                                    '<span class="postedPageRequiredSkills">&emsp;Expected Skills : </span><span class="numberOfApplications">Number of Applied candidates : </span><br>'+
                                                    '<span><button class="btn btn-primary btn-sm applyButton"   onclick="applyForJob('+i+')">Apply</button></span><br>'+
                                                    '<small><span class="postedPagePostedBy">posted by : </span></small>'+
                                                '</div>'+
                                            '</div> '+
                                        '</div><br>';                 
                    $("#postedJobsCards").append(postedJobsCard);
                    $(".postedPageCardTitle")[i].append(AvailableJobJson["companyName"+i]+", ");
                    $(".postedPageCardTitleLocation")[i].append(AvailableJobJson["location"+i]);
                    $(".statusFlag")[i].append(AvailableJobJson["vacancyStatus"+i]);
                    $(".postedPageCardPost")[i].append(AvailableJobJson["post"+i]);
                    $(".postedPageDepartment")[i].append(AvailableJobJson["department"+i]);
                    $(".postedPageRequiredExperience")[i].append(AvailableJobJson["requiredExperience"+i]+" year(s)");
                    $(".postedPageRequiredSkills")[i].append(AvailableJobJson["skills"+i]);
                    $(".postedPagePostedBy")[i].append(AvailableJobJson["postedBy"+i]);
                    var statusFlag = document.getElementsByClassName("statusFlag");
                    var applyButton = document.getElementsByClassName("applyButton");
                    if(AvailableJobJson["applicationStatus"+i]!=null){
                        applyButton[i].innerHTML=AvailableJobJson["applicationStatus"+i];
                        applyButton[i].disabled="true";
                        applicationsCount++;
                    }
                    if(statusFlag[i].innerHTML=="Closed"){  
                        applyButton[i].disabled = "true";
                        statusFlag[i].setAttribute("class","badge badge-danger statusFlag");
                        // $(".applyButton")[i].setAttribute("data-toggle","tooltip");
                        // $(".applyButton")[i].setAttribute("title","Applications No Longer Accepted !");
                    }
                    $(".numberOfApplications")[i].innerHTML+=applicationsCount;
                }
                $("#postedJobsCards").prepend("<h3 id='availableJobsHead'>Available Opportunities</h3>");
            }
        }
    }
    xhttp.open("GET","PostedJobsForSeekers?",true);
    xhttp.send();
}
function togglePostedJobStatus(i){
    var status=document.getElementsByClassName("statusButton")[i].innerHTML;
    console.log(status);
    if(status=="Closed"){
        status="Open";
    }
    else if(status=="Open"){
        status="Closed";
    }
    var bool=confirm("Change Status to "+status+" ?");
    var postid=PostedJobJson["postId"+i];
    console.log(postid);
    if(bool==true){
        var xhttp=new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(this.readyState == 4 && this.status == 200){
                if(status=="Closed"){
                    var statusButton = document.getElementsByClassName("statusButton");
                    statusButton[i].parentNode.setAttribute("class","btn btn-danger btn-sm");
                    statusButton[i].innerHTML=status;
                }
                if(status=="Open"){
                    var statusButton = document.getElementsByClassName("statusButton");
                    statusButton[i].parentNode.setAttribute("class","btn btn-success btn-sm");
                    statusButton[i].innerHTML=status;
                }
            }
        }
        xhttp.open("GET","TogglePostedJobStatus?postid="+postid+"&status="+status,true);
        xhttp.send();
    }
}
function applyForJob(i){
    $("#applyJobsForm").show();
    $("#currentMobileNumber").val(AvailableJobJson["userMobileNumber"]);
    $("#experience").val(AvailableJobJson["userExperienceYears"]);
    $("#currentLocation").val(AvailableJobJson["userLocation"]);
    $("#employeeHomeBody").css("opacity","0.5");
    $("#applyPostId").val(i);
    $("#applyCloseForm").click(function(){
        document.getElementById("applyNewJobForm").reset();
        $("#applyJobsForm").hide();
        $("#employeeHomeBody").css("opacity","1");
      });
}
function viewApps(){
    console.log("90");
    var xhttp=new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(this.readyState == 4 && this.status == 200){
                console.log(this.responseText);
            }
        }
    xhttp.open("GET","viewApplications",true);
    xhttp.send();
}
function showEmployeeProfile(){
    $("#postedJobsCards").hide();
    $("#userProfile").css("display","block");
}