Feature: sample karate test script
  for help, see: https://github.com/intuit/karate/wiki/IDE-Support

  Background:
    * url 'https://jsonplaceholder.typicode.com'
    * def seller_service = '/sellers'
    * def product_service = '/products'
    * def random_string =
                   """function(s) {
                     var text = "";
                     var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
                     for (var i = 0; i < s; i++)
#                       text += possible.charAt(Math.floor(Math.random() * possible.length));
                     return text;}"""
    * def random_number = """function(max){ return Math.floor(Math.random() * max) }"""

  Scenario: create seller and product, retrieve them
    #create seller
    * def nickname = random_string(5)
    When path seller_service
    And request read('create_seller.json') {nickname: '#(nickname)'}
    And method post
    Then status 201
    And match response contains {nickname: '#{nickname}'}

    * def seller_id = response.id

    #get seller by id
    When path seller_service, seller_id
    And method get
    Then status 200
    And match response contains {nickname: '#{nickname}'}

    #create product
    * def title = random_string(5)
    When path product_service
    And request read(create_product.json) {"sellerId": '#{seller_id}', "title": '#{title}', "price": '#{random_number(3)}', "stock": '#{random_number(2)}'}
    And method post
    Then status 201
    And match response contains {title: '#{title}'}

    * def product_id = response.id

    #get product by product id
    When path product_service, product_id
    And method get
    Then status 200
    And match response contains {title: '#{title}'}

  Scenario: retrieve sellers
    When path seller_service
    And method get
    Then status 200
#    And size>=1 kontrolu eklenebilir

  Scenario: retrieve products
    When path product_service
    And method get
    Then status 200
#    And size>=1 kontrolu eklenebilir