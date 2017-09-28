package panucci.julian.apiexample

/**
 * Created by dks0398255 on 9/28/17.
 */

data class User(
        val login: String,
        val id: Long,
        val url: String,
        val html_url: String,
        val followers_url: String,
        val following_url: String,
        val starred_url: String,
        val gists_url: String,
        val type: String,
        val score: Double
)


/**
 * These represent the parameters of the json that comes back
 * Ex: {
 *      total_count: 1
 *      incomplete_results: false
 *      items: [
 *      - {
 *              login: "Julp04",
                id: 8071655,
                avatar_url: "https://avatars0.githubusercontent.com/u/8071655?v=4",
                gravatar_id: "",
                url: "https://api.github.com/users/Julp04",
                html_url: "https://github.com/Julp04",
                followers_url: "https://api.github.com/users/Julp04/followers",
                following_url: "https://api.github.com/users/Julp04/following{/other_user}",
                gists_url: "https://api.github.com/users/Julp04/gists{/gist_id}",
                starred_url: "https://api.github.com/users/Julp04/starred{/owner}{/repo}",
                subscriptions_url: "https://api.github.com/users/Julp04/subscriptions",
                organizations_url: "https://api.github.com/users/Julp04/orgs",
                repos_url: "https://api.github.com/users/Julp04/repos",
                events_url: "https://api.github.com/users/Julp04/events{/privacy}",
                received_events_url: "https://api.github.com/users/Julp04/received_events",
                type: "User",
                site_admin: false,
                score: 40.436512
 *         }
 *              ]
 *      }
 *  }
 */
data class Result (val total_count: Int, val incomplete_results: Boolean, val items: List<User>)