'use strict';

angular.module('myApp').controller('ContactController', ['$scope', 'ContactService', function($scope, ContactService) {
    var self = this;
    self.contact={id:null,firstName:'',lastName:'',phoneNumber:''};
    self.contacts=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllContacts();

    function fetchAllContacts(){
        ContactService.fetchAllContacts()
            .then(
            function(d) {
                self.contacts = d;
            },
            function(errResponse){
                console.error('Error while fetching Contacts');
            }
        );
    }

    function createContact(contact){
        ContactService.createContact(contact)
            .then(
            fetchAllContacts,
            function(errResponse){
                console.error('Error while creating Contact');
            }
        );
    }

    function updateContact(contact, id){
        ContactService.updateContact(contact, id)
            .then(
            fetchAllContacts,
            function(errResponse){
                console.error('Error while updating Contact');
            }
        );
    }

    function deleteContact(id){
        ContactService.deleteContact(id)
            .then(
            fetchAllContacts,
            function(errResponse){
                console.error('Error while deleting Contact');
            }
        );
    }

    function submit() {
        if(self.contact.id===null){
            console.log('Saving New Contact', self.contact);
            createContact(self.contact);
        }else{
            updateContact(self.contact, self.contact.id);
            console.log('Contact updated with id ', self.contact.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.contacts.length; i++){
            if(self.contacts[i].id === id) {
                self.contact = angular.copy(self.contacts[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.contact.id === id) {//clean form if the contact to be deleted is shown there.
            reset();
        }
        deleteContact(id);
    }


    function reset(){
        self.contact={id:null,firstName:'',lastName:'',phoneNumber:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
