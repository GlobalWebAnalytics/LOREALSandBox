
  // Replace with your client ID from the developer console.
  var CLIENT_ID = '542893179414-f6s6gn42kari01oi0ggbtsda5on9qi9b.apps.googleusercontent.com';

  // Set authorized scope.
  var SCOPES = ['https://www.googleapis.com/auth/analytics.readonly'];


  function authorize(event) {
    var authData = {
      client_id: CLIENT_ID,
      scope: SCOPES,
      immediate: false
    };

    gapi.auth.authorize(authData, function(response) {
      var authButton = document.getElementById('auth-button');
      if (response.error) {
        console.log("response.error");
      }
      else {
    	document.querySelector("#mainContainer paper-icon-item").setAttribute("hidden", "true");
        queryAccounts();
      }
    });
  }


function queryAccounts() {
  // Load the Google Analytics client library.
  gapi.client.load('analytics', 'v3').then(function() {

    // Get a list of all Google Analytics accounts for this user
    gapi.client.analytics.management.accounts.list().then(handleAccounts);
  });
}


function handleAccounts(response) {
  // Handles the response from the accounts list method.
  if (response.result.items && response.result.items.length) {
    // Get the first Google Analytics account.
    var firstAccountId = response.result.items[0].id;

    // Query for properties.
    queryProperties(firstAccountId);
  } else {
    console.log('No accounts found for this user.');
  }
}


function queryProperties(accountId) {
  // Get a list of all the properties for the account.
  gapi.client.analytics.management.webproperties.list(
      {'accountId': accountId})
    .then(handleProperties)
    .then(null, function(err) {
      // Log any errors.
      console.log(err);
  });
}


function handleProperties(response) {
  // Handles the response from the webproperties list method.
  if (response.result.items && response.result.items.length) {

    // Get the first Google Analytics account
    var firstAccountId = response.result.items[0].accountId;

    // Get the first property ID
    var firstPropertyId = response.result.items[0].id;

    // Query for Views (Profiles).
    queryProfiles(firstAccountId, firstPropertyId);
  } else {
    console.log('No properties found for this user.');
  }
}


function queryProfiles(accountId, propertyId) {
  // Get a list of all Views (Profiles) for the first property
  // of the first Account.
  gapi.client.analytics.management.profiles.list({
      'accountId': accountId,
      'webPropertyId': propertyId
  })
  .then(handleProfiles)
  .then(null, function(err) {
      // Log any errors.
      console.log(err);
  });
}


function handleProfiles(response) {
  // Handles the response from the profiles list method.
  if (response.result.items && response.result.items.length) {
    // Get the first View (Profile) ID.
    var firstProfileId = response.result.items[0].id;

    // Query the Core Reporting API.
    queryCoreReportingApi(firstProfileId);
  } else {
    console.log('No views (profiles) found for this user.');
  }
}


function queryCoreReportingApi(profileId) {
  // Query the Core Reporting API for the number sessions for
  // the past seven days.
  gapi.client.analytics.data.ga.get({
    'ids': 'ga:' + profileId,
    'start-date': '7daysAgo',
    'end-date': 'today',
    'metrics': 'ga:sessions'
  })
  .then(function(response) {
    var formattedJson = JSON.stringify(response.result, null, 2);
    document.getElementById('query-output').value = formattedJson;
  })
  .then(null, function(err) {
      // Log any errors.
      console.log(err);
  });
  listAccounts();
}

/*
 * Note: This code assumes you have an authorized Analytics client object.
 * See the Account Developer Guide for details.
 */

/*
 * Example 1:
 * Requests a list of all accounts for the authorized user.
 */
function listAccounts() {
  var request = gapi.client.analytics.management.accounts.list();
  request.execute(printAccounts);
}

/*
 * Example 2:
 * The results of the list method are passed as the results object.
 * The following code shows how to iterate through them.
 */
function printAccounts(results) {
  if (results && !results.error) {
    var accounts = results.items;
    for (var i = 0, account; account = accounts[i]; i++) {
      console.log('Account Id: ' + account.id);
      console.log('Account Kind: ' + account.kind);
      console.log('Account Name: ' + account.name);
      console.log('Account Created: ' + account.created);
      console.log('Account Updated: ' + account.updated);
    }
  }
}