#RailRakshak

This project was directed towards the ease of filing an FIR ([First Information Report](https://en.wikipedia.org/wiki/First_information_report "FIR")) to the Railway Police Force (RPF) in Mumbai,IN using a web portal from any location.

This project was intended to make things easy for the victim as well as the police when it comes to filing documentation.

- Victim Side
 
  * We can understand the plight of a victim when they are robbed, hurt, assaulted, etc. on a moving vehicle or the platform, and help them file an FIR online without the feeling of being judged or having to go all the way to the nearest RPF quarters.

  * To validate themselves, they have to verify an OTP on their cell number and give some basic information. If they wish to create an account to autofill some details in the future, they may even create an account with us. Their details shall be stored in a pgSQL database.

  * They can then enter the mandatory details in the form that follow a standard FIR filing format (that Bandra RPF were kind enough to share with us). They can share their incident, estimated place of occurance, and contact details for the force to get back to them. 

  * The form also records the sender's IP address, location and sender's device details to track miscreants who want to prank the force.

   * The victim then receives an OCR document in the same format as an FIR and they can download it.

- Police Side
