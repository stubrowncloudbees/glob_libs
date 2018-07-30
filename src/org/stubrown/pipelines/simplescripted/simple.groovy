#!/usr/bin/groovy
package org.stubrown.pipelines.simplescripted;

def build(name) {
    sh "building ${name}"
}
def test(name) {
    sh "echo testing ${name}"
}
def deploy(env,app) {}
   sh "deploying ${name} to ${app}"
// AimTheory have a recommendation and explanation about this here
    return this