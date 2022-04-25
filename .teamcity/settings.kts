import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.investigationsAutoAssigner
import jetbrains.buildServer.configs.kotlin.projectFeatures.activeStorage
import jetbrains.buildServer.configs.kotlin.projectFeatures.s3Storage
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2022.04"

project {

    buildType(Build)

    features {
        s3Storage {
            id = "PROJECT_EXT_139"
            bucketName = "kilina-bucket2-private"
            bucketPrefix = "25apr"
            cloudFrontEnabled = true
            cloudFrontUploadDistribution = "E41EEYSGLZNSV"
            cloudFrontDownloadDistribution = "E41EEYSGLZNSV"
            cloudFrontPublicKeyId = "K26BUJLPKKPL16"
            cloudFrontPrivateKey = "credentialsJSON:3fd6888d-e40e-48fd-b39a-4b60954ad0ca"
            accessKey = "credentialsJSON:087ba199-e5e9-4bc8-a011-1d47370f2a07"
            awsEnvironment = default {
                awsRegionName = "us-east-2"
            }
            accessKeyID = "AKIA5JH2VERVMSFJ2JGO"
        }
        activeStorage {
            id = "PROJECT_EXT_140"
            activeStorageID = "PROJECT_EXT_139"
        }
    }
}

object Build : BuildType({
    name = "Build"

    artifactRules = """
        test => test
        README.md
    """.trimIndent()

    vcs {
        root(DslContext.settingsRoot)
    }

    triggers {
        vcs {
        }
    }

    features {
        investigationsAutoAssigner {
            defaultAssignee = "test"
            excludeUsers = """
                ksenia
                admin
            """.trimIndent()
            ignoreCompilationProblems = "true"
        }
    }
})
