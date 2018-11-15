create DATABASE IF NOT EXISTS mutants;

use mutants;

create table IF NOT EXISTS checkeddna (
  id bigint not null auto_increment, 
  created datetime, 
  dna longtext, 
  hash varchar(40), 
  result bit not null, 
  primary key (id));
  
create index IX_result on checkeddna (result);

alter table checkeddna add constraint UK_hash unique (hash);
