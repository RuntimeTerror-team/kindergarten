        if (username.trim().length < 8) {

            this.setState({ validationPassed: false })
        }

        if (username.trim().length > 20) {

            this.setState({ validationPassed: false })
        }

        if (password.trim().length < 8) {

            this.setState({ validationPassed: false })

        }

        if (new RegExp("^(?!(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,}))").test(password)) {

            this.setState({ validationPassed: false })
        }