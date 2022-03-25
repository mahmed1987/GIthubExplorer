package com.syphyr.dawn.githubexplorer.common.system

sealed class Failure {
  abstract val reason: String
  abstract val id: Int

  class Login(override val id: Int, override val reason: String, val exception: Exception?=null) :
    Failure()
  class Firestore(override val id: Int, override val reason: String) :
    Failure()
  class Android(override val reason: String, override val id: Int): Failure()
}