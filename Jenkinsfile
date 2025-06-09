// pipeline {
//     agent any
//     options {
//         skipDefaultCheckout(true)
//     }
//     stages {
//         stage("Delete workspace and clone from SCM") {
//             steps {
//                 cleanWs()
//                 checkout scm
//                 echo "Building ${env.JOB_NAME}..."
//             }
//         }
//
//         stage("Build") {
//             steps {
//                 withMaven(
//                         maven:'Maven'
//                 ){
//                     // Run the maven build
//                     bat "mvn clean install"
//                 }
//             }
//         }
//     }
//
//     post {
//         always {
//             echo "Job completed with status: ${currentBuild.currentResult}"
//         }
//         success {
//             emailext body:"""Dear all,<br><br> Kindly find attached the App response report, build log and Test report, for <strong>NextGen Mobile App</strong> Critical Use cases<br>
//                 with a status of <strong>Successful</strong><br>
//                 The following table shows the status of the test cases: <br><br>
//                 Test Automation Team""",
//                 attachmentsPattern: 'Reports/*.html',
//                 from: 'test.automation@mtn.com',
//                 mimeType: 'text/html',
//                 replyTo: '',
//                 subject: "My Report Template",
//                 attachLog: true,
//                 to: "chukwudi.okoli@tmnxtsupport.com,Ramanjaneyulu@techmsupport.com"
//
//         }
//         failure {
//             emailext body:"""Dear all,<br><br> Kindly find attached the App response report, build log and Test report, for <strong>NextGen Mobile App</strong> Critical Use cases<br>
//                 with a status of <strong>Failed</strong><br>
//                 The following table shows the status of the test cases: <br><br>
//                 Test Automation Team""",
//                 attachmentsPattern: 'Reports/*.html',
//                 from: 'test.automation@mtn.com',
//                 mimeType: 'text/html',
//                 replyTo: '',
//                 subject: "My Report Template",
//                 attachLog: true,
//                 to: "chukwudi.okoli@tmnxtsupport.com,Ramanjaneyulu@techmsupport.com"
//         }
//         unstable {
//             emailext body:"""Dear all,<br><br> Kindly find attached the App response report, build log and Test report, for <strong>NextGen Mobile App</strong> Critical Use cases<br>
//                 with a status of <strong>Unstable</strong><br>
//                 The following table shows the status of the test cases: <br><br>
//                 Test Automation Team""",
//                 attachmentsPattern: 'Reports/*.html',
//                 from: 'test.automation@mtn.com',
//                 mimeType: 'text/html',
//                 replyTo: '',
//                 subject: "My Report Template",
//                 attachLog: true,
//                 to: "chukwudi.okoli@tmnxtsupport.com,Ramanjaneyulu@techmsupport.com"
//         }
//     }
// }

pipeline {
    agent any
    options {
        skipDefaultCheckout(true)
    }
    stages {
        stage("Delete workspace and clone from SCM") {
            steps {
                cleanWs()
                checkout scm
                echo "Building ${env.JOB_NAME}..."
            }
        }

        stage("Build") {
            steps {
                withMaven(maven:'Maven'){
                    // Run the maven build
                    bat "mvn clean install"}

                }
            }
        }
    }

    post {
        always {
            echo "Job completed with status: ${currentBuild.currentResult}"
               script{
                def emailBody = readFile 'body.html'
                env.SIMPLEREPORT = emailBody
               }
        }
        success {
                emailext body: """ success ${SIMPLEREPORT}""",
                    attachmentsPattern: 'Reports/*.html',
                    from: 'mtntest726@gmail.com',
                    mimeType: 'text/html',
                    replyTo: '',
                    subject: "My Report Template",
                    attachLog: true,
                    to: "chukwudi.okoli@tmnxtsupport.com,Ramanjaneyulu@techmsupport.com,okolichukwudi34@gmail.com"

        }
        failure {
                emailext body: """failure ${SIMPLEREPORT}""",
                    attachmentsPattern: 'Reports/*.html',
                    from: 'mtntest726@gmail.com',
                    mimeType: 'text/html',
                    replyTo: '',
                    subject: "My Report Template",
                    attachLog: true,
                    to: "chukwudi.okoli@tmnxtsupport.com,Ramanjaneyulu@techmsupport.com,okolichukwudi34@gmail.com"

        }
        unstable {
                emailext body: """skipped ${SIMPLEREPORT}""",
                    attachmentsPattern: 'Reports/*.html',
                    from: 'mtntest726@gmail.com',
                    mimeType: 'text/html',
                    replyTo: '',
                    subject: "My Report Template",
                    attachLog: true,
                    to: "chukwudi.okoli@tmnxtsupport.com,Ramanjaneyulu@techmsupport.com,okolichukwudi34@gmail.com"

        }
    }

