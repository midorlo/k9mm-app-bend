#!/bin/sh

#
#   Backup a container
#

#   Launch a new container and mount the volume from the dbstore container
#   Mount a local host directory as /backup
#   Pass a command that tars the contents of the dbdata volume to a backup.tar file inside our /backup directory.

docker run --rm --volumes-from dbstore -v "$(pwd)":~/.k9/backups/ ubuntu tar cvf ~/.k9/backups/backups/.tar ~/.k9/volumes/postgres

#   When the command completes and the container stops, we are left with a backup of our dbdata volume.
#   Restore container from backup
#   With the backup just created, you can restore it to the same container, or another that you made elsewhere.
#   For example, create a new container named dbstore2

docker run -v ~/.k9/volumes/postgres --name dbstore2 ubuntu /bin/bash

#   Then un-tar the backup file in the new container`s data volume:

docker run --rm --volumes-from dbstore2 -v "$(pwd)":/backup ubuntu bash -c "cd ~/.k9/volumes/postgres && tar xvf ~/.k9/backups/backup.tar --strip 1"