#!/usr/bin/groovy
package org.stubrown.pipelines.simplescripted;

def build() {
    sh "echo building "
}
def test(name) {
    sh "echo testing ${name}"
}
def deploy(env,app) {}
   sh "deploying ${name} to ${app}"
// AimTheory have a recommendation and explanation about this here
    return this