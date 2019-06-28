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
    $(document).on('keydown', function(event) {
        if (event.key == "Escape") {
             $("#postJobsForm").hide();
             $("#applyJobsForm").hide();
//             document.getElementById("postNewJobForm").reset();
             $("#employerHomeBody").css("opacity","1");
             $("#employeeHomeBody").css("opacity","1");
        }
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
            if(jsonSize==0) $("#postedJobsCards").prepend("<h5 id='postedJobsHead'>No Jobs Posted</h5>");
            else
            { 
                for(var i=0;i<jsonSize;i++){
                    // $('#filterSelectTag').append("<option></option>").attr("value","Developer").text("Developer");
                    var postedJobsCard='<div class="card">'+
                                            '<div class="card-body">'+
                                                '<h4 class="card-title"><span class="postedPageCardTitle"></span><span><small class="postedPageCardTitleLocation"></small></span><span><button class="btn btn-success btn-sm" onclick="togglePostedJobStatus('+i+')"><span class="statusButton"></span>&nbsp;&#9660;</button></span></h4>'+
                                                '<div class="postedJobsCardBody">'+
                                                    '<span class="postedPageCardPost">&emsp;Vacancies in Post : </span>'+
                                                    '&emsp;&emsp;<span class="postedPageDepartment">Salary : </span>'+
                                                    '&emsp;&emsp;&emsp;<span class="postedPageRequiredExperience">Expected Experience : </span><br><br>'+
                                                    '<span class="postedPageRequiredSkills">&emsp;Expected Skills : </span><span class="numberOfApplications">Number of Applied candidates : </span><br>'+
                                                    '<br><small><span class="postedPagePostedBy">posted by : </span></small>'+
                                                '</div>'+
                                            '</div> '+
                                        '</div><br>';        //applicationsCount         
                    $("#postedJobsCards").append(postedJobsCard);
                    $(".postedPageCardTitle")[i].append(PostedJobJson["companyName"+i]+", ");
                    $(".postedPageCardTitleLocation")[i].append(PostedJobJson["location"+i]);
                    $(".statusButton")[i].append(PostedJobJson["vacancyStatus"+i]);
                    $(".postedPageCardPost")[i].append(PostedJobJson["post"+i]);
                    $(".postedPageDepartment")[i].append(PostedJobJson["salary"+i]);
                    $(".numberOfApplications")[i].append(PostedJobJson["applicationsCount"+i]);                    
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
                var selectTag = document.getElementById("filterSelectTag");
                for(var i=0;i<jsonSize;i++){
                    var option = document.createElement("option");
                    option.text = AvailableJobJson["department"+i];
                    selectTag.add(option);
                }
                // filterFunction();
                var selectTag = document.getElementById("filterSelectTag1");
                for(var i=0;i<jsonSize;i++){
                    var option = document.createElement("option");
                    option.text = "Rs."+AvailableJobJson["salary"+i];
                    selectTag.add(option);
                }
                filterFunction();
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
    $("#filterJobs").hide();
    $("#userProfile").css("display","block");
    $("#ProfileCurrentMobileNumber").val(AvailableJobJson["userMobileNumber"]);
    $("#ProfileExperience").val(AvailableJobJson["userExperienceYears"]);
    $("#ProfileCurrentLocation").val(AvailableJobJson["userLocation"]);
    $("#profilePreferedDesignations").val(AvailableJobJson["userPreferedDesignations"]);
}
function showEmployeeHome(){
    $("#userProfile").css("display","none");
    $("#postedJobsCards").show();
    $("#filterJobs").show();
}
function getPreferences(){
    var loc= window.location.href;
    var locArr=loc.split("/").pop();
    var newUrl=loc.replace(locArr,"GetPreferences.action");
    location.replace(newUrl);
}

function loadPreferrences(){
    var label = document.getElementsByClassName("label");
    for(var i=0;i<label.length;i++){
        if(label[i].innerHTML=="OtherPreferences:"){
            label[i].style="display:none";
        }
    }
    var pref = document.getElementsByClassName("checkboxLabel");
    for(var i=0;i<pref.length;i++){
        pref[i].setAttribute("onclick","showOtherPreferences("+i+");");
        pref[i].checked=false;
    }
    var pref = document.getElementsByName("colors");
    for(var i=0;i<pref.length;i++){
        pref[i].setAttribute("onclick","showOtherPreferences("+i+");");
        pref[i].checked=false;
    }
}
function showOtherPreferences(j){
    var pref=document.getElementsByName("colors");
    var prefLabel = document.getElementsByClassName("checkboxLabel");
    if(pref[j].checked==true){
        if(prefLabel[j].innerHTML=="Other"){
            document.getElementById("OtherPreferences").style="display:block";
            var label = document.getElementsByClassName("label");
            for(var i=0;i<label.length;i++){
                if(label[i].innerHTML=="OtherPreferences:"){
                    label[i].style="display:block";
                }
            }
        }
    }
    else{
        if(prefLabel[j].innerHTML=="Other"){
            document.getElementById("OtherPreferences").style="display:none";
            var label = document.getElementsByClassName("label");
            for(var i=0;i<label.length;i++){
                if(label[i].innerHTML=="OtherPreferences:"){
                    label[i].style="display:none";
                }
            }
        }
    }
}
$(document).ready(function(){
    $("#profileApplyNewJobForm :input").change(function() {
        document.getElementById("profileApplyNewJobForm").setAttribute("changed","true");
    });
});
function enableEditing(){
    document.getElementById("ProfileCurrentMobileNumber").removeAttribute("readonly");
    document.getElementById("ProfileCurrentLocation").removeAttribute("readonly");
    document.getElementById("ProfileExperience").removeAttribute("readonly");
    document.getElementById("profilePreferedDesignations").removeAttribute("readonly");
}
function filterFunction(){
    var jsonSize=AvailableJobJson["size"];
    var selectTagByDesginations  = document.getElementById("filterSelectTag");
    var selectTagBySalary = document.getElementById("filterSelectTag1");
    document.getElementById("postedJobsCards").innerHTML="";
    var cardCount=0;
    for(var i=0;i<jsonSize;i++){
        if(selectTagByDesginations.value!=AvailableJobJson["department"+i] && selectTagByDesginations.value!="none"){
            continue;
        }
        if(selectTagBySalary.value!="Rs."+AvailableJobJson["salary"+i] && selectTagBySalary.value!="none"){
            continue;
        }
        console.log(selectTagByDesginations.value+"zzz"+AvailableJobJson["department"+i]);
        var applicationsCount = 0;
        var postedJobsCard='<div class="card">'+
                                '<div class="card-body">'+
                                    '<h4 class="card-title"><span class="postedPageCardTitle"></span><span><small class="postedPageCardTitleLocation"></small></span><span class="badge badge-success statusFlag"></span></h4>'+
                                    '<div class="postedJobsCardBody"><br>'+
                                        '<span class="postedPageCardDepartment">&emsp;Designation : </span>'+
                                        '&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span class="postedPageSalary">Salary : </span>'+
                                        '&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span class="postedPageRequiredExperience">Expected Experience : </span><br><br>'+
                                        '<span class="postedPageRequiredSkills">&emsp;Expected Skills : </span><span class="numberOfApplications">Number of Applied candidates : </span><br>'+
                                        '<span><button class="btn btn-primary btn-sm applyButton"   onclick="applyForJob('+i+')">Apply</button></span><br>'+
                                        '<small><span class="postedPagePostedBy">posted by : </span></small>'+
                                    '</div>'+
                                '</div> '+
                            '</div><br>';                 
        $("#postedJobsCards").append(postedJobsCard);
        $(".postedPageCardTitle")[cardCount].append(AvailableJobJson["companyName"+i]+", ");
        $(".postedPageCardTitleLocation")[cardCount].append(AvailableJobJson["location"+i]);
        $(".statusFlag")[cardCount].append(AvailableJobJson["vacancyStatus"+i]);
        $(".postedPageCardDepartment")[cardCount].append(AvailableJobJson["department"+i]);
        $(".postedPageSalary")[cardCount].append(AvailableJobJson["salary"+i]);
        $(".postedPageRequiredExperience")[cardCount].append(AvailableJobJson["requiredExperience"+i]+" year(s)");
        $(".postedPageRequiredSkills")[cardCount].append(AvailableJobJson["skills"+i]);
        $(".postedPagePostedBy")[cardCount].append(AvailableJobJson["postedBy"+i]);
        var statusFlag = document.getElementsByClassName("statusFlag");
        var applyButton = document.getElementsByClassName("applyButton");
        if(AvailableJobJson["applicationStatus"+i]!=null){
            applyButton[cardCount].innerHTML=AvailableJobJson["applicationStatus"+i];
            applyButton[cardCount].disabled="true";
            applicationsCount++;
        }
        if(statusFlag[cardCount].innerHTML=="Closed"){  
            applyButton[cardCount].disabled = "true";
            statusFlag[cardCount].setAttribute("class","badge badge-danger statusFlag");
        }
        $(".numberOfApplications")[cardCount].innerHTML+=applicationsCount;
        cardCount++;
    } 
    var none=document.getElementById("postedJobsCards");
    if(none.innerHTML==""){
        console.log("none");
        none.innerHTML+="<br><h4 style='text-align:center;'>No Jobs Available for applied Filter</h4>";
    }
    $("#postedJobsCards").prepend("<h3 id='availableJobsHead'>Available Opportunities</h3>");
}