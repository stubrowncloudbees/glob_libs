package com.foo.utils

public void dockerTemplate(body) {
    podTemplate(label: 'label',
            containers: [containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true)],
            volumes: [hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')]) {
        body()
    }
}

public void mavenTemplate(body) {
    podTemplate(label: 'label',
            containers: [containerTemplate(name: 'maven', image: 'maven', command: 'cat', ttyEnabled: true)],
            volumes: [secretVolume(secretName: 'maven-settings', mountPath: '/root/.m2'),
                      persistentVolumeClaim(claimName: 'maven-local-repo', mountPath: '/root/.m2nrepo')]) {
        body()
    }
}

return this
