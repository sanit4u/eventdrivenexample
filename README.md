# eventdrivenexample
1. It's an example to show case account creation with send sending notification in an event driven style.
2. This repository is not finished yet.
3. futher, will integrate credit card data services with architecture.

## TroubleShooting
1. mongodb , mongobee integration problem
:  query failed with error code 13 and error message 'not authorized on to execute command

  solution: 
  the mongobee reads the system index. but the user with which the mongobee is authenticating cannot read system.indicies. because, new mongodb release.
  Hence, create role readWriteSystem
  db.createRole({role : "readWriteSystem", privileges: [{resource: { db: "testdb", collection: "system.indexes" }, actions: [ "changeStream", "collStats", "convertToCapped", "createCollection", "createIndex", "dbHash", "dbStats", "dropCollection", "dropIndex", "emptycapped", "find", "insert", "killCursors", "listCollections", "listIndexes", "planCacheRead", "remove", "renameCollectionSameDB", "update" ]}], roles:[]})
  db.grantRolesToUser('testuser', ['readWriteSystem'])
  https://stackoverflow.com/questions/57721306/getting-not-authorized-for-query-on-testdb-system-indexes-src-mongo-db-commands/57870446#57870446
