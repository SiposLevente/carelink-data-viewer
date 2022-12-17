# carelink-data-viewer
A small application that shows the current glucose value in a browser page. It also displays the current data in the title of the page.

## Starting the app
Run the `start.bat` or the `start.sh` to start the app. Then navigate to http://localhost:8080/ site to view the glucose data.

## Configuring the app
Edit the `settings.conf`file to your CareLink login data to access the readings. **Please do not share your `settings.conf` file or it's content with anybody. It's content is sensitive information!!!**

### Valid settings
- `username`: Your CareLink's username.
- `password`: Your CareLink's password.
- `country_code`: Your CareLink's country (e.g.: Hungary -> `hu`, United States -> `us`)

## Disclamer
**Do not use the values displayed for threapial reasons as readings can be inaccurate! It is recomended that you wash your hands and measure your blood glucose with a blood glucose meter before injecting additional insulin or eating carbohidrates for a low value!**

## Credits
CareLink Java client is from [benceszasz](https://github.com/benceszasz). Repository: [https://github.com/benceszasz/CareLinkJavaClient](https://github.com/benceszasz/CareLinkJavaClient).
